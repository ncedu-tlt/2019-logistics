package ru.ncedu.logistics.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class LifecycleTest extends HttpServlet {

    private static final LocalDate STATIC_DATE = LocalDate.now();
    private static final AtomicInteger STATIC_COUNTER = new AtomicInteger();

    private final LocalDate creationDate = LocalDate.now();
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter responseWriter = resp.getWriter();
        responseWriter.write("Static date " + STATIC_DATE + "\n");
        responseWriter.write("Static counter " + STATIC_COUNTER.incrementAndGet() + "\n");
        responseWriter.write("Instance date " + creationDate + "\n");
        responseWriter.write("Instance counter " + counter.incrementAndGet() + "\n");
        responseWriter.write("Press F5 to refresh this page");
    }

}
