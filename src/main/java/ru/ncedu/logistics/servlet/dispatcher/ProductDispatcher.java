package ru.ncedu.logistics.servlet.dispatcher;

public class ProductDispatcher extends AbstractDispatcher {

    public ProductDispatcher(){
        parsers.put(new UrlParser("/products"), "/GetAllProductsServlet");
        parsers.put(new UrlParser("/products/create"), "/CreateProductServlet");
        parsers.put(new UrlParser("/products/{productId}"), "/GetProductServlet");
        parsers.put(new UrlParser("/products/{productId}/edit"), "/EditProductServlet");
        parsers.put(new UrlParser("/products/{productId}/delete"), "/DeleteProductServlet");
    }
}
