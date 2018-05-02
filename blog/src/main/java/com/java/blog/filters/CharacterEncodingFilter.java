package com.java.blog.filters;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    protected String encoding = null;
    protected FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (encoding != null) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setContentType("application/json;charset=" + encoding);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        this.encoding = null;
        this.filterConfig =null;
    }
}
