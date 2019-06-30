package ru.ncedu.logistics.servlet.offering;

import ru.ncedu.logistics.data.entity.OfferingId;
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

public class EditOfferingServlet extends HttpServlet {

    @Inject
    private OfferingService offeringService;

    @Inject
    private ProductService productService;

    @Inject
    private OfficeService officeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int officeId = Integer.parseInt((String) req.getAttribute("officeId"));
        int productId = Integer.parseInt((String) req.getAttribute("productId"));
        OfferingId offeringId = new OfferingId();

        offeringId.setOfficeId(officeId);
        offeringId.setProductId(productId);

        OfferingDTO offering = offeringService.findById(offeringId);
        List<ProductDTO> productsList = productService.findAll();

        req.setAttribute("offering", offering);
        req.setAttribute("productsList", productsList);
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "false");
        req.setAttribute("action", "/EditOfferingServlet");

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

        offering = offeringService.update(offering);

        req.setAttribute("officeId", officeId);
        req.setAttribute("productId", productId);

        resp.sendRedirect("/offerings/" + officeId + "/" + productId + "/edit");
    }
}
