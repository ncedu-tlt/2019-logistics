package ru.ncedu.logistics.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OfficeCheckServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String operation = req.getParameter("operation");
        if(operation == null){
        } else {
            switch (operation) {
                case "edit":
                    req.getRequestDispatcher("OfficeEditServlet").forward(req, resp);
                    break;
                case "create":
                    req.getRequestDispatcher("OfficeCreateServlet").forward(req, resp);
                    break;
                default:
                    PrintWriter printWriter = resp.getWriter();
                    printWriter.println("Unknown value of operation!");
                    break;
            }
        }
    }
}
