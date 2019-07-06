package ru.ncedu.logistics.servlet.road;

import ru.ncedu.logistics.data.entity.RoadId;
import ru.ncedu.logistics.dto.RoadDTO;
import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.RoadService;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SearchRoadsServlet extends HttpServlet {

    @Inject
    private RoadService roadService;

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TownDTO> towns = townService.findAll();
        req.setAttribute("towns", towns);
        req.getRequestDispatcher("roads.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String leftParam = req.getParameter("leftId");
        String rightParam = req.getParameter("rightId");

        List<TownDTO> towns = townService.findAll();
        List<RoadDTO> foundRoads = new LinkedList<>();

        if(leftParam != null && rightParam != null){
            int leftId = Integer.parseInt(leftParam);
            int rightId = Integer.parseInt(rightParam);

            RoadId roadId = new RoadId();
            roadId.setLeftId(leftId);
            roadId.setRightId(rightId);

            if(roadService.existsById(roadId)) {
                RoadDTO singleRoad = roadService.findById(roadId);
                foundRoads.add(singleRoad);
            }

            req.setAttribute("leftId", leftId);
            req.setAttribute("rightId", rightId);
        } else {
            if(leftParam != null){
                int leftId = Integer.parseInt(leftParam);
                if(roadService.existsByTownId(leftId)) {
                    foundRoads = roadService.findByTownId(leftId);
                }
                req.setAttribute("leftId", leftId);
            } else {
                int rightId = Integer.parseInt(rightParam);
                if(roadService.existsByTownId(rightId)) {
                    foundRoads = roadService.findByTownId(rightId);
                }
                req.setAttribute("rightId", rightId);
            }
        }

        if(foundRoads.size() == 0){
            req.setAttribute("isGet", "false");
        } else {
            req.setAttribute("foundRoads", foundRoads);
            req.setAttribute("isGet", "true");
        }

        req.setAttribute("towns", towns);
        req.getRequestDispatcher("roads.jsp").forward(req, resp);
    }
}
