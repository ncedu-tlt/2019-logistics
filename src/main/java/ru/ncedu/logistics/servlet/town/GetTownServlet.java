package ru.ncedu.logistics.servlet.town;

import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetTownServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int townId = Integer.parseInt((String) req.getAttribute("townId"));

        TownDTO town = townService.findById(townId);

        req.setAttribute("town", town);
        req.setAttribute("isRO", "true");
        req.setAttribute("isCreated", "false");

        req.getRequestDispatcher("townEdit.jsp").forward(req, resp);
    }

}
