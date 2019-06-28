package ru.ncedu.logistics.servlet.town;

import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditTownServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param = req.getParameter("townId");
        Integer townId;
        if(param == null){
            townId = Integer.parseInt((String) req.getAttribute("townId"));
        } else {
            townId = Integer.parseInt(param);
        }

        TownDTO town = townService.findById(townId);

        req.setAttribute("action", "/EditTownServlet");
        req.setAttribute("town", town);
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "false");

        req.getRequestDispatcher("townEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int townId = Integer.parseInt(req.getParameter("townId"));

        String townName = req.getParameter("townName");

        TownDTO townDTO = new TownDTO();
        townDTO.setId(townId);
        townDTO.setName(townName);

        townDTO = townService.update(townDTO);

        req.setAttribute("town", townDTO);
        req.setAttribute("action", "/EditTownServlet");
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "false");

        req.getRequestDispatcher("townEdit.jsp").forward(req, resp);
    }
}
