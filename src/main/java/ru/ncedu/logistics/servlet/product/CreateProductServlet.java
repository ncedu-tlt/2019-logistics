package ru.ncedu.logistics.servlet.product;

import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.service.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "/CreateProductServlet");
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "true");

        req.getRequestDispatcher("productEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String townName = req.getParameter("productName");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(townName);

        productDTO = productService.create(productDTO);

        req.setAttribute("productId", productDTO.getId());
        resp.sendRedirect("/products/" + productDTO.getId() + "/edit");
    }
}
