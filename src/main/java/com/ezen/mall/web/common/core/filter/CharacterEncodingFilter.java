package com.ezen.mall.web.common.core.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * 문자 인코딩 서블릿 필터
 */
@WebFilter(
    urlPatterns = {"/mvc/*"},
    initParams = {
        @WebInitParam(name = "encoding", value = "utf-8")
    }
)
public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("[debug] : CharacterEncodingFilter init()");
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("[debug] : 요청 파라메터에 대한 문자인코딩 필터 실행됨...");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=" + encoding);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("[debug] : CharacterEncodingFilter destroy()");
    }
}
