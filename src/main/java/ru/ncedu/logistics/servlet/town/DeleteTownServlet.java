package ru.ncedu.logistics.servlet.town;

import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTownServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int townId = Integer.parseInt(req.getParameter("townId"));
        townService.deleteById(townId);
        resp.sendRedirect("/towns");
    }
}
