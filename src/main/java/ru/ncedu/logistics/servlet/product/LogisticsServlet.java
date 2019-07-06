package ru.ncedu.logistics.servlet.product;

import ru.ncedu.logistics.dto.OfferingDTO;
import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.dto.RoadDTO;
import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.*;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LogisticsServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Inject
    private ProductService productService;

    @Inject
    private LogisticsService logisticsService;

    @Inject
    private OfferingService offeringService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TownDTO> towns = townService.findAll();
        List<ProductDTO> products = productService.findAll();

        req.setAttribute("towns", towns);
        req.setAttribute("products", products);

        req.getRequestDispatcher("logistics.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int initialTownId = Integer.parseInt(req.getParameter("townId"));
        int productId = Integer.parseInt(req.getParameter("productId"));

        Map<Integer, Double> townsCost = logisticsService.getTownsCost(initialTownId);

        //Find town with min cost
        Integer minTownId = null;
        Double minTownCost = null;
        OfferingDTO minOffer = new OfferingDTO();
        minOffer.setPrice(0.0);

        for(Map.Entry<Integer, Double> pair : townsCost.entrySet()){
            if(offeringService.existsMinOffer(pair.getKey(), productId)) {
                OfferingDTO offer = offeringService.findMinOffer(pair.getKey(), productId);
                double curSum = pair.getValue() + offer.getPrice();
                if (minTownCost == null || curSum < minTownCost + minOffer.getPrice()) {
                    minTownId = pair.getKey();
                    minTownCost = pair.getValue();
                    minOffer = offer;
                }
            }
        }

        if(minTownId == null){
            req.setAttribute("isGet", "false");
        } else {
            //Find path from min town
            List<RoadDTO> pathRoad = logisticsService.findRoads(minTownCost, townsCost);

            double productPrice = minOffer.getPrice();
            double totalPrice = minTownCost * 0.5 + productPrice;
            TownDTO town = townService.findById(minTownId);
            double distance = minTownCost;

            req.setAttribute("isGet", "true");
            req.setAttribute("path", pathRoad);
            req.setAttribute("minOffer", minOffer);
            req.setAttribute("totalPrice", totalPrice);
            req.setAttribute("distance", distance);
            req.setAttribute("minTown", town);
            req.setAttribute("yourTown", townService.findById(initialTownId).getName());
        }

        req.getRequestDispatcher("logisticsResult.jsp").forward(req, resp);

    }
}
