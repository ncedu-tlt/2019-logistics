package ru.ncedu.logistics.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.logging.Logger;

public class AccessLog implements Filter {

    private final static Logger LOGGER = Logger.getLogger(AccessLog.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            String url = ((HttpServletRequest)servletRequest).getRequestURL().toString();
            LOGGER.info("User " + servletRequest.getRemoteAddr() + " opened " + url);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
