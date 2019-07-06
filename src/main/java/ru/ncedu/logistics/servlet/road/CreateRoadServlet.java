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
import java.util.List;

public class CreateRoadServlet extends HttpServlet {

    @Inject
    private RoadService roadService;

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<TownDTO> towns = townService.findAll();

        req.setAttribute("towns", towns);
        req.setAttribute("action", "/roads/create");
        req.setAttribute("isCreated", "true");

        req.getRequestDispatcher("roadEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int leftId = Integer.parseInt(req.getParameter("leftId"));
        int rightId = Integer.parseInt(req.getParameter("rightId"));
        double distance = Double.parseDouble(req.getParameter("distance"));

        if(leftId > rightId){
            int temp = leftId;
            leftId = rightId;
            rightId = temp;
        }

        RoadDTO road = new RoadDTO();

        RoadId roadId = new RoadId();
        roadId.setLeftId(leftId);
        roadId.setRightId(rightId);

        road.setLeftTown(townService.findById(leftId));
        road.setRightTown(townService.findById(rightId));
        road.setDistance(distance);

        if(roadService.existsById(roadId)){
            req.setAttribute("errorCreate", "true");
        } else {
            roadService.create(road);
            road = roadService.findById(roadId);
        }

        List<TownDTO> towns = townService.findAll();

        req.setAttribute("isCreated", "false");
        req.setAttribute("road", road);
        req.setAttribute("towns", towns);
        req.setAttribute("action", "/EditRoadServlet");

        req.getRequestDispatcher("roadEdit.jsp").forward(req, resp);
    }
}
