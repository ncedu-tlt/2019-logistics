package ru.ncedu.logistics.servlet.offering;

import ru.ncedu.logistics.dto.OfficeDTO;
import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.dto.TownDTO;
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
import java.util.List;

public class SearchOfferingsServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Inject
    private OfficeService officeService;

    @Inject
    private ProductService productService;

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
        resp.getWriter().println("This should be list of offerings but something gonna wrong.. :(");
    }
}
