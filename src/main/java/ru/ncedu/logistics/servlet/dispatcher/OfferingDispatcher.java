package ru.ncedu.logistics.servlet.dispatcher;

import java.util.HashMap;
import java.util.Map;

public class OfferingDispatcher extends AbstractDispatcher {

    private static final Map<UrlParser, String> PARSERS = new HashMap<>();
    static {
        PARSERS.put(new UrlParser("/offerings/search"), "/SearchOfferingsServlet");
        PARSERS.put(new UrlParser("/offerings/create"), "/CreateOfferingServlet");
        PARSERS.put(new UrlParser("/offerings/{officeId}/{productId}/edit"), "/EditOfferingServlet");
        PARSERS.put(new UrlParser("/offerings/{officeId}/{productId}/delete"), "/DeleteOfferingServlet");
    }

    public OfferingDispatcher(){
        super(PARSERS);
    }
}
