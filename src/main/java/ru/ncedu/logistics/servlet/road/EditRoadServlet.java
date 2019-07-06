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

public class EditRoadServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Inject
    private RoadService roadService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int leftId = Integer.parseInt((String) req.getAttribute("leftId"));
        int rightId = Integer.parseInt((String) req.getAttribute("rightId"));

        if(leftId > rightId){
            int temp = leftId;
            leftId = rightId;
            rightId = temp;
        }

        RoadId roadId = new RoadId();
        roadId.setLeftId(leftId);
        roadId.setRightId(rightId);

        RoadDTO road = roadService.findById(roadId);
        List<TownDTO> towns = townService.findAll();

        req.setAttribute("action", "/" + leftId + "/" + rightId + "/edit");
        req.setAttribute("towns", towns);
        req.setAttribute("road", road);
        req.setAttribute("isCreated", "false");

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
            road = roadService.update(road);
            req.setAttribute("action", "/" + leftId + "/" + rightId + "/edit");
            req.setAttribute("isCreated", "false");
        } else {
            req.setAttribute("action", "/roads/create");
            req.setAttribute("errorEdit", "true");
        }

        List<TownDTO> towns = townService.findAll();

        req.setAttribute("towns", towns);
        req.setAttribute("road", road);

        req.getRequestDispatcher("roadEdit.jsp").forward(req, resp);
    }
}
