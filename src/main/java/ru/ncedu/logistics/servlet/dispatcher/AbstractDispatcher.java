package ru.ncedu.logistics.servlet.dispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public abstract class AbstractDispatcher extends HttpServlet {

    private final Map<UrlParser, String> PARSERS;

    public AbstractDispatcher(Map<UrlParser, String> parsers){
        this.PARSERS = parsers;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatch(req, resp);
    }

    public void dispatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String url = req.getRequestURL().toString();

        for(Map.Entry<UrlParser, String> parser: PARSERS.entrySet()) {
            if(parser.getKey().matches(url)) {
                Map<String, String> attrs = parser.getKey().parse(url);
                for (Map.Entry<String, String> attr: attrs.entrySet()) {
                    req.setAttribute(attr.getKey(), attr.getValue());
                }
                req.getRequestDispatcher(parser.getValue()).forward(req, resp);
                return;
            }
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
