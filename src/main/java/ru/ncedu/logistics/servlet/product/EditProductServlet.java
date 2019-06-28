package ru.ncedu.logistics.servlet.product;

import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.service.ProductService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param = req.getParameter("productId");
        Integer productId;
        if(param == null){
            productId = Integer.parseInt((String) req.getAttribute("productId"));
        } else {
            productId = Integer.parseInt(param);
        }

        ProductDTO product = productService.findById(productId);

        req.setAttribute("action", "/EditProductServlet");
        req.setAttribute("product", product);
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "false");

        req.getRequestDispatcher("productEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int productId = Integer.parseInt(req.getParameter("productId"));
        String productName = req.getParameter("productName");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        productDTO.setName(productName);

        productDTO = productService.update(productDTO);

        req.setAttribute("product", productDTO);
        req.setAttribute("action", "/EditProductServlet");
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "false");

        req.getRequestDispatcher("productEdit.jsp").forward(req, resp);
    }
}
