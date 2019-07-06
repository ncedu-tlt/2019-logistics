package ru.ncedu.logistics.servlet.dispatcher;

import java.util.HashMap;
import java.util.Map;

public class ProductDispatcher extends AbstractDispatcher {

    private static final Map<UrlParser, String> PARSERS = new HashMap<>();
    static {
        PARSERS.put(new UrlParser("/products"), "/GetAllProductsServlet");
        PARSERS.put(new UrlParser("/products/create"), "/CreateProductServlet");
        PARSERS.put(new UrlParser("/products/{productId}"), "/GetProductServlet");
        PARSERS.put(new UrlParser("/products/{productId}/edit"), "/EditProductServlet");
        PARSERS.put(new UrlParser("/products/{productId}/delete"), "/DeleteProductServlet");
    }

    public ProductDispatcher(){
        super(PARSERS);
    }
}
