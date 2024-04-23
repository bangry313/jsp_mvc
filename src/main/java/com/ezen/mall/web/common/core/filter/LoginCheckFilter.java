package com.ezen.mall.web.common.core.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 로그인 여부 체크 서블릿 필터
 */
//@WebFilter(urlPatterns = {"/*"})
public class LoginCheckFilter implements Filter {
    private List<String> loginNoCheckList;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        loginNoCheckList = new ArrayList<>();
        loginNoCheckList.add("/mvc/");
        loginNoCheckList.add("/mvc/sample/hello");
        loginNoCheckList.add("/mvc/sample/today");
        loginNoCheckList.add("/mvc/member/register");
        loginNoCheckList.add("/mvc/member/login");
        loginNoCheckList.add("/mvc/member/login-action");
        loginNoCheckList.add("/mvc/member/idcheck");
        loginNoCheckList.add("/mvc/board/list");
        loginNoCheckList.add("/mvc/board/read");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestUri = httpRequest.getRequestURI();
        System.out.println("[debug] : 요청 URI : " + requestUri);
        if(!loginNoCheckList.contains(requestUri)){
            System.out.println("[debug] : 인증이 필요한 요청");
            HttpSession session = httpRequest.getSession(false);
            if (session == null ||  session.getAttribute("loginMember") == null) {
                httpRequest.setAttribute("message", "로그인 사용자만 접근이 허용된 페이지입니다.");
                String referer = httpRequest.getHeader("referer");
                request.setAttribute("referer", referer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/member/login");
                dispatcher.forward(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {  }
}
