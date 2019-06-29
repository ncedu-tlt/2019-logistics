package ru.ncedu.logistics.servlet.office;

import ru.ncedu.logistics.dto.OfficeDTO;
import ru.ncedu.logistics.service.OfficeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetOfficeServlet extends HttpServlet {

    @Inject
    private OfficeService officeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int officeId = Integer.parseInt((String) req.getAttribute("officeId"));

        OfficeDTO officeDTO = officeService.findById(officeId);

        req.setAttribute("office", officeDTO);
        req.setAttribute("isRO", "true");

        req.getRequestDispatcher("officeEdit.jsp").forward(req, resp);
    }
}
