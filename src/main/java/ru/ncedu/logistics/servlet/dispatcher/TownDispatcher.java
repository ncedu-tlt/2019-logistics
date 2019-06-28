package ru.ncedu.logistics.servlet.dispatcher;

public class TownDispatcher extends AbstractDispatcher{

    public TownDispatcher() {
        parsers.put(new UrlParser("/towns"), "/GetAllTownsServlet");
        parsers.put(new UrlParser("/towns/{townId}"), "/GetTownServlet");
        parsers.put(new UrlParser("/towns/create"), "/CreateTownServlet");
        parsers.put(new UrlParser("/towns/{townId}/edit"), "/EditTownServlet");
        parsers.put(new UrlParser("/towns/{townId}/delete"), "/DeleteTownServlet");
        parsers.put(new UrlParser("/towns/{townId}/offices"), "/GetOfficesInTownServlet");
    }

}
