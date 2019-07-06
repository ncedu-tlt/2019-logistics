package ru.ncedu.logistics.servlet.office;

import ru.ncedu.logistics.dto.OfficeDTO;
import ru.ncedu.logistics.dto.TownDTO;
import ru.ncedu.logistics.service.OfficeService;
import ru.ncedu.logistics.service.TownService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateOfficeServlet extends HttpServlet {

    @Inject
    private OfficeService officeService;

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int townId = Integer.parseInt(req.getParameter("townId"));
        List<TownDTO> townsList = townService.findAll();

        req.setAttribute("action", "/CreateOfficeServlet");
        req.setAttribute("townId", townId);
        req.setAttribute("townsList", townsList);
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "true");

        req.getRequestDispatcher("officeEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int townId = Integer.parseInt(req.getParameter("townId"));
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));

        if(officeService.existsByTownAndPhone(townId, phoneNumber)){
            int id = officeService.findByTownAndPhone(townId, phoneNumber).getId();
            req.setAttribute("officeId", id);
            req.setAttribute("isRO", "false");
            req.setAttribute("isCreated", "false");

            resp.sendRedirect("/offices/" + id + "/edit");
        } else {
            TownDTO townDTO = new TownDTO();
            townDTO.setId(townId);

            OfficeDTO officeDTO = new OfficeDTO();
            officeDTO.setPhone(phoneNumber);
            officeDTO.setTown(townDTO);

            officeDTO = officeService.create(officeDTO);

            req.setAttribute("officeId", officeDTO.getId());
            req.setAttribute("isRO", "false");
            req.setAttribute("isCreated", "false");

            resp.sendRedirect("/offices/" + officeDTO.getId() + "/edit");
        }
    }
}
