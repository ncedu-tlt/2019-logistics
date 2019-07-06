package ru.ncedu.logistics.servlet.dispatcher;

public class RoadDispatcher extends AbstractDispatcher {

    public RoadDispatcher(){
        parsers.put(new UrlParser("/roads/search"), "/SearchRoadsServlet");
        parsers.put(new UrlParser("/roads/create"), "/CreateRoadServlet");
        parsers.put(new UrlParser("/roads/{leftId}/{rightId}/edit"), "/EditRoadServlet");
        parsers.put(new UrlParser("/roads/{leftId}/{rightId}/delete"), "/DeleteRoadServlet");
    }
}
