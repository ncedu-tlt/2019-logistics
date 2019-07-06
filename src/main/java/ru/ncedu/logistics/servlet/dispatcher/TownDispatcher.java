package ru.ncedu.logistics.servlet.dispatcher;

import java.util.HashMap;
import java.util.Map;

public class TownDispatcher extends AbstractDispatcher{

    private static final Map<UrlParser, String> PARSERS = new HashMap<>();
    static {
        PARSERS.put(new UrlParser("/towns"), "/GetAllTownsServlet");
        PARSERS.put(new UrlParser("/towns/{townId}"), "/GetTownServlet");
        PARSERS.put(new UrlParser("/towns/create"), "/CreateTownServlet");
        PARSERS.put(new UrlParser("/towns/{townId}/edit"), "/EditTownServlet");
        PARSERS.put(new UrlParser("/towns/{townId}/delete"), "/DeleteTownServlet");
        PARSERS.put(new UrlParser("/towns/{townId}/offices"), "/GetOfficesInTownServlet");
    }


    public TownDispatcher() {
        super(PARSERS);
    }

}
