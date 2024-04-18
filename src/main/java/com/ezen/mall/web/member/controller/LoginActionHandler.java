package com.ezen.mall.web.member.controller;

import com.ezen.mall.domain.member.dto.Member;
import com.ezen.mall.domain.member.service.MemberService;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;
import com.ezen.mall.web.common.encription.EzenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class LoginActionHandler implements Handler {

    private MemberService memberService = new MemberService();

    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName =  "redirect:/";

        String id = paramMap.get("loginid");
        String passwd = paramMap.get("loginpw");
        String saveId = paramMap.get("saveid");
        String referer = paramMap.get("referer");
        if(referer != null && !referer.equals("")){
            viewName = "redirect:" + referer;
        }

        Member loginMember = memberService.login(id, passwd);
        // 회원인 경우
        if(loginMember != null){
            if(saveId != null){
                Cookie saveIdCookie = new Cookie("saveId", EzenUtil.encription(id));
                saveIdCookie.setMaxAge(60*60*24*100); // 100일저장
                saveIdCookie.setPath("/");
                response.addCookie(saveIdCookie);
            }else{
                Cookie[] cookies = request.getCookies();
                if(cookies != null){
                    for(Cookie cookie : cookies){
                        if(cookie.getName().equals("saveId")){
                            cookie.setPath("/");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
            }
            request.getSession().setAttribute("loginMember", loginMember);
            return viewName;
        } else { // 회원이 아닌경우..
            response.setContentType("text/html; charset=utf-8");
            try {
                PrintWriter out = response.getWriter();
                out.println("<script>");
                out.println("alert('아이디와 비밀번호를 확인하세요.')");
                out.println("location.href='/member/login.jsp'");
                out.println("</script>");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return viewName;
    }
}
