package ru.ncedu.logistics.servlet.dispatcher;

public class OfferingDispatcher extends AbstractDispatcher {

    public OfferingDispatcher(){
        parsers.put(new UrlParser("/offerings/search"), "/SearchOfferingsServlet");
        parsers.put(new UrlParser("/offerings/create"), "/CreateOfferingServlet");
        parsers.put(new UrlParser("/offerings/{officeId}/{productId}/edit"), "/EditOfferingServlet");
        parsers.put(new UrlParser("/offerings/{officeId}/{productId}/delete"), "/DeleteOfferingServlet");
    }
}
