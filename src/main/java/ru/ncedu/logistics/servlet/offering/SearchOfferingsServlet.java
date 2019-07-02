package ru.ncedu.logistics.servlet.offering;

import ru.ncedu.logistics.data.entity.OfferingId;
import ru.ncedu.logistics.dto.OfferingDTO;
import ru.ncedu.logistics.dto.OfficeDTO;
import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.OfferingService;
import ru.ncedu.logistics.service.OfficeService;
import ru.ncedu.logistics.service.ProductService;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SearchOfferingsServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Inject
    private ProductService productService;

    @Inject
    private OfferingService offeringService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<TownDTO> towns = townService.findAll();
        List<ProductDTO> products = productService.findAll();

        req.setAttribute("towns", towns);
        req.setAttribute("products", products);

        req.getRequestDispatcher("/offering.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramProdId = req.getParameter("productId");
        String paramOfId = req.getParameter("officeId");

        if(paramProdId != null){
            if(paramOfId != null){
                //User sent productId and officeId
                int productId = Integer.parseInt(paramProdId);
                int officeId = Integer.parseInt(paramOfId);
                int townId = Integer.parseInt(req.getParameter("townId"));

                List<TownDTO> towns = townService.findAll();
                List<ProductDTO> products = productService.findAll();

                OfferingId offeringId = new OfferingId();

                offeringId.setProductId(productId);
                offeringId.setOfficeId(officeId);

                OfferingDTO singleOffer = offeringService.findById(offeringId);

                List<OfferingDTO> offers = new LinkedList<>();
                offers.add(singleOffer);

                req.setAttribute("offers", offers);
                req.setAttribute("isGet", "true");
                req.setAttribute("prodIdServ", productId);
                req.setAttribute("townIdServ", townId);
                req.setAttribute("towns", towns);
                req.setAttribute("products", products);

                req.getRequestDispatcher("/offering.jsp").forward(req, resp);

            } else {
                //User sent only productId
                int productId = Integer.parseInt(paramProdId);

                List<TownDTO> towns = townService.findAll();
                List<ProductDTO> products = productService.findAll();

                List<OfferingDTO> offers = offeringService.findByProductId(productId);

                req.setAttribute("offers", offers);
                req.setAttribute("isGet", "true");
                req.setAttribute("prodIdServ", productId);
                req.setAttribute("towns", towns);
                req.setAttribute("products", products);

                req.getRequestDispatcher("/offering.jsp").forward(req, resp);

            }
        } else {
            //User sent only officeId
            int officeId = Integer.parseInt(paramOfId);
            int townId = Integer.parseInt(req.getParameter("townId"));

            List<TownDTO> towns = townService.findAll();
            List<ProductDTO> products = productService.findAll();

            List<OfferingDTO> offers = offeringService.findByOfficeId(officeId);

            req.setAttribute("offers", offers);
            req.setAttribute("isGet", "true");
            req.setAttribute("townIdServ", townId);
            req.setAttribute("towns", towns);
            req.setAttribute("products", products);

            req.getRequestDispatcher("/offering.jsp").forward(req, resp);
        }
    }
}
