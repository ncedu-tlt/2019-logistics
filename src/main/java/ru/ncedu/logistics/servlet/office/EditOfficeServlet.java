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

public class EditOfficeServlet extends HttpServlet {

    @Inject
    private OfficeService officeService;

    @Inject
    private TownService townService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param = req.getParameter("officeId");
        Integer officeId;

        if(param == null){
            officeId = Integer.parseInt((String) req.getAttribute("officeId"));
        } else {
            officeId = Integer.parseInt(param);
        }

        OfficeDTO officeDTO = officeService.findById(officeId);
        List<TownDTO> townsList = townService.findAll();

        req.setAttribute("action", "/EditOfficeServlet");
        req.setAttribute("isRO", "false");
        req.setAttribute("isCreated", "false");
        req.setAttribute("townsList", townsList);
        req.setAttribute("office", officeDTO);

        req.getRequestDispatcher("officeEdit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int officeId = Integer.parseInt(req.getParameter("officeId"));
        int townId = Integer.parseInt(req.getParameter("townId"));
        int phoneNumber = Integer.parseInt(req.getParameter("phoneNumber"));

        TownDTO townDTO = new TownDTO();
        townDTO.setId(townId);

        OfficeDTO officeDTO = new OfficeDTO();
        officeDTO.setId(officeId);
        officeDTO.setPhone(phoneNumber);
        officeDTO.setTown(townDTO);

        officeDTO = officeService.update(officeDTO);
        List<TownDTO> townsList = townService.findAll();

        req.setAttribute("action", "/EditOfficeServlet");
        req.setAttribute("townsList", townsList);
        req.setAttribute("office", officeDTO);
        req.setAttribute("officeId", officeDTO.getId());

        resp.sendRedirect("/offices/" + officeDTO.getId() + "/edit");
    }
}
