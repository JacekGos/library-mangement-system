package com.example.LibrarySystemWebApplication.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CrossSiteScriptingXSSFilter", value = "/*")
public class CrossSiteScriptingXSSFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        chain.doFilter(new CrossSiteScriptingXSSRequestWrapper((HttpServletRequest) request), response);
    }
}
