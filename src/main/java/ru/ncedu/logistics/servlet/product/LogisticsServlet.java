package ru.ncedu.logistics.servlet.product;

import ru.ncedu.logistics.dto.OfferingDTO;
import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.dto.RoadDTO;
import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.OfferingService;
import ru.ncedu.logistics.service.ProductService;
import ru.ncedu.logistics.service.RoadService;
import ru.ncedu.logistics.service.TownService;

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
    private RoadService roadService;

    @Inject
    private OfferingService offeringService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TownDTO> towns = townService.findAll();
        List<ProductDTO> products = productService.findAll();

        req.setAttribute("towns", towns);
        req.setAttribute("products", products);

        req.getRequestDispatcher("logistic.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TownDTO> towns = townService.findAll();
        List<ProductDTO> products = productService.findAll();
        int initialTownId = Integer.parseInt(req.getParameter("townId"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        Map<Integer, Double> townsCost = new HashMap<>();
        Set<Integer> townsWithChangedCost = new HashSet<>();

        townsCost.put(initialTownId, 0.0);
        townsWithChangedCost.add(initialTownId);

        while(!townsWithChangedCost.isEmpty()){
            Set<Integer> townsWithChangedCostNew = new HashSet<>();

            for(int id : townsWithChangedCost){
                if(roadService.existsByTownId(id)) {
                    List<RoadDTO> roads = roadService.findByTownId(id);

                    for (RoadDTO road : roads) {
                        int nextId = (road.getLeftTown().getId().equals(id)) ? road.getRightTown().getId() : road.getLeftTown().getId();

                        double newCost = townsCost.get(id) + road.getDistance();
                        Double nextTownCost = townsCost.get(nextId);

                        if(nextTownCost == null || newCost < nextTownCost){
                            townsCost.put(nextId, newCost);
                            townsWithChangedCostNew.add(nextId);
                        }
                    }
                }
            }

            townsWithChangedCost = townsWithChangedCostNew;
        }

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
            List<TownDTO> pathToClient = new LinkedList<>();
            List<RoadDTO> pathRoad = new LinkedList<>();
            pathToClient.add(townService.findById(minTownId));

            double stepCost = minTownCost;
            while (stepCost > 0) {
                for (Map.Entry<Integer, Double> pair : townsCost.entrySet()) {
                    int nextTownId = pair.getKey();
                    List<RoadDTO> roads = roadService.findByTownId(nextTownId);
                    for (RoadDTO road : roads) {
                        if (stepCost - road.getDistance() == pair.getValue()) {
                            pathToClient.add(townService.findById(nextTownId));
                            pathRoad.add(road);
                            stepCost -= road.getDistance();
                            break;
                        }
                    }
                }
            }

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

        req.getRequestDispatcher("result.jsp").forward(req, resp);

    }
}
