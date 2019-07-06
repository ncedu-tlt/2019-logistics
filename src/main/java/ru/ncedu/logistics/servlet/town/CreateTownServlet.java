package ru.ncedu.logistics.servlet.town;

import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTownServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("action", "/CreateTownServlet");
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "true");

        req.getRequestDispatcher("townEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String townName = req.getParameter("townName");

        if(townService.existsByName(townName)){
            int townId = townService.findByName(townName).getId();
            req.setAttribute("townId", townId);
            resp.sendRedirect("/towns/" + townId + "/edit");
        } else {
            TownDTO townDTO = new TownDTO();
            townDTO.setName(townName);

            townDTO = townService.create(townDTO);

            req.setAttribute("townId", townDTO.getId());
            resp.sendRedirect("/towns/" + townDTO.getId() + "/edit");
        }

    }
}
