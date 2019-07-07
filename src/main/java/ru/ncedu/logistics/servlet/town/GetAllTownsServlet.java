package ru.ncedu.logistics.servlet.town;

import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllTownsServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TownDTO> towns = townService.findAll();
        req.setAttribute("townsList", towns);
        req.getRequestDispatcher("towns.jsp").forward(req, resp);
    }

}
