package ru.ncedu.logistics.servlet;

import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TownListServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter responseWriter = resp.getWriter();
        List<TownDTO> towns = townService.findAll();
        for(TownDTO townDTO: towns) {
            responseWriter.write(townDTO.getId() + " " + townDTO.getName() + "\n");
        }
    }

}
