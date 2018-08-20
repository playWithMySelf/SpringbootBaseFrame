package com.jw.filter;

import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "SessionFilter", urlPatterns = { "/*", "*" })
public class SessionFilter implements Filter {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        System.out.println("SessionFilter:"+url);
        logger.info("SessionFilter");
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {

    }
}
