package ru.ncedu.logistics.servlet.dispatcher;

import java.util.HashMap;
import java.util.Map;

public class RoadDispatcher extends AbstractDispatcher {

    private static final Map<UrlParser, String> PARSERS = new HashMap<>();
    static {
        PARSERS.put(new UrlParser("/roads/search"), "/SearchRoadsServlet");
        PARSERS.put(new UrlParser("/roads/create"), "/CreateRoadServlet");
        PARSERS.put(new UrlParser("/roads/{leftId}/{rightId}/edit"), "/EditRoadServlet");
        PARSERS.put(new UrlParser("/roads/{leftId}/{rightId}/delete"), "/DeleteRoadServlet");
    }

    public RoadDispatcher(){
        super(PARSERS);
    }
}
