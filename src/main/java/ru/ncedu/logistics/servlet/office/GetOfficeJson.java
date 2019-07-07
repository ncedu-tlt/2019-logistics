package ru.ncedu.logistics.servlet.office;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.ncedu.logistics.dto.OfficeDTO;
import ru.ncedu.logistics.service.OfficeService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetOfficeJson extends HttpServlet {

    @Inject
    private OfficeService officeService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        List<OfficeDTO> offices = officeService.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(offices);

        resp.getOutputStream().println(json);

    }
}
