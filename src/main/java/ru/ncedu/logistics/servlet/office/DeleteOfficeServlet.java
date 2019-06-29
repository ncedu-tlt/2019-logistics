package ru.ncedu.logistics.servlet.office;

import ru.ncedu.logistics.dto.OfficeDTO;
import ru.ncedu.logistics.service.OfficeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteOfficeServlet extends HttpServlet {

    @Inject
    private OfficeService officeService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int officeId = Integer.parseInt(req.getParameter("officeId"));
        int townId = officeService.findById(officeId).getTown().getId();

        officeService.deleteById(officeId);

        req.setAttribute("townId", townId);

        resp.sendRedirect("/towns/" + townId + "/offices");
    }
}
