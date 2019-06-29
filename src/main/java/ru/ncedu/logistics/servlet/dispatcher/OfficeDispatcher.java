package ru.ncedu.logistics.servlet.dispatcher;

public class OfficeDispatcher extends AbstractDispatcher {

    public OfficeDispatcher(){
        parsers.put(new UrlParser("/offices/{officeId}"),"/GetOfficeServlet");
        parsers.put(new UrlParser("/offices/create"),"/CreateOfficeServlet");
        parsers.put(new UrlParser("/offices/{officeId}/edit"),"/EditOfficeServlet");
        parsers.put(new UrlParser("/offices/{officeId}/delete"),"/DeleteOfficeServlet");
    }
}
