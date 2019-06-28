package ru.ncedu.logistics.servlet.town;

import ru.ncedu.logistics.dto.OfficeDTO;
import ru.ncedu.logistics.service.OfficeService;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetOfficesInTownServlet extends HttpServlet {

    @Inject
    private TownService townService;

    @Inject
    private OfficeService officeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int townId = Integer.parseInt(req.getParameter("townId"));

        String townName = townService.findById(townId).getName();
        List<OfficeDTO> officesById = officeService.findByTownId(townId);

        req.setAttribute("townName", townName);
        req.setAttribute("officesById", officesById);

        req.getRequestDispatcher("offices.jsp").forward(req,resp);
    }
}
