package com.example.LibrarySystemWebApplication.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "NoSessionFilter", value = "/test1")
public class NoSessionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        HttpServletRequest filterRequest = (HttpServletRequest) request;
        HttpServletResponse filterResponse = (HttpServletResponse) response;

        filterResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        filterResponse.setHeader("Pragma", "no-cache");
        filterResponse.setDateHeader("Expires", 0);

        HttpSession session = filterRequest.getSession(false);

        System.out.println("in NoSessionFilter");
//        && !(session.getAttribute("userName") == null)
        if (session != null && !session.isNew()) {
            chain.doFilter(request, response);
        } else {
            RequestDispatcher requestDispatcher = filterRequest.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(filterRequest, filterResponse);
        }
    }
}
