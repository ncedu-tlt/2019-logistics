package ru.ncedu.logistics.servlet.dispatcher;

import java.util.HashMap;
import java.util.Map;

public class OfficeDispatcher extends AbstractDispatcher {

    private static final Map<UrlParser, String> PARSERS = new HashMap<>();
    static {
        PARSERS.put(new UrlParser("/offices/{officeId}"),"/GetOfficeServlet");
        PARSERS.put(new UrlParser("/offices/create"),"/CreateOfficeServlet");
        PARSERS.put(new UrlParser("/offices/{officeId}/edit"),"/EditOfficeServlet");
        PARSERS.put(new UrlParser("/offices/{officeId}/delete"),"/DeleteOfficeServlet");
    }

    public OfficeDispatcher(){
        super(PARSERS);
    }
}
