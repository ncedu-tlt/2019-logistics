package ru.ncedu.logistics.servlet.offering;

import ru.ncedu.logistics.data.entity.OfferingId;
import ru.ncedu.logistics.service.OfferingService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOfferingServlet extends HttpServlet {

    @Inject
    private OfferingService offeringService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int officeId = Integer.parseInt(req.getParameter("officeId"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        OfferingId offeringId = new OfferingId();

        offeringId.setOfficeId(officeId);
        offeringId.setProductId(productId);

        offeringService.deleteById(offeringId);

        resp.sendRedirect("/offices/" + officeId + "/edit");
    }
}
