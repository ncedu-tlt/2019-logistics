package ru.ncedu.logistics.servlet.product;

import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.service.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productId = Integer.parseInt((String) req.getAttribute("productId"));

        ProductDTO product = productService.findById(productId);

        req.setAttribute("product", product);
        req.setAttribute("isRO", "true");
        req.setAttribute("isCreated", "false");

        req.getRequestDispatcher("productEdit.jsp").forward(req, resp);
    }
}
