package ru.ncedu.logistics.servlet.road;

import ru.ncedu.logistics.data.entity.RoadId;
import ru.ncedu.logistics.service.RoadService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRoadServlet extends HttpServlet {

    @Inject
    private RoadService roadService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        if (roadService.existsById(roadId)){
            roadService.delete(roadId);
        }

        resp.sendRedirect("/roads/search");
    }
}
