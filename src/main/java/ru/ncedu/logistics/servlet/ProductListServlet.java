package ru.ncedu.logistics.servlet;

import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.service.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductListServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDTO> productList = productService.findAll();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("products.jsp").forward(req,resp);
    }
}
