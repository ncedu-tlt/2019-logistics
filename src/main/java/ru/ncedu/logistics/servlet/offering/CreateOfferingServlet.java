package ru.ncedu.logistics.servlet.offering;

import ru.ncedu.logistics.dto.OfferingDTO;
import ru.ncedu.logistics.dto.OfficeDTO;
import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.service.OfferingService;
import ru.ncedu.logistics.service.OfficeService;
import ru.ncedu.logistics.service.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateOfferingServlet extends HttpServlet {

    @Inject
    private OfferingService offeringService;

    @Inject
    private ProductService productService;

    @Inject
    private OfficeService officeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int officeId = Integer.parseInt(req.getParameter("officeId"));
        List<ProductDTO> productsList = productService.findAll();

        req.setAttribute("officeId", officeId);
        req.setAttribute("productsList", productsList);
        req.setAttribute("isCreated", "true");
        req.setAttribute("isRO", "false");
        req.setAttribute("action", "/CreateOfferingServlet");

        req.getRequestDispatcher("offeringEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int officeId = Integer.parseInt(req.getParameter("officeId"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        double price = Double.parseDouble(req.getParameter("price"));

        OfferingDTO offering = new OfferingDTO();
        OfficeDTO office = officeService.findById(officeId);
        ProductDTO product = productService.findById(productId);

        offering.setOffice(office);
        offering.setProduct(product);
        offering.setPrice(price);

        offering = offeringService.create(offering);

        resp.sendRedirect("/");
    }
}
