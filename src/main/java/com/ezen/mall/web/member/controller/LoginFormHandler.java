package com.ezen.mall.web.member.controller;

import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;
import com.ezen.mall.web.common.encription.EzenUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LoginFormHandler implements Handler {
    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String saveId = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("saveId")) {
                    saveId = EzenUtil.decription(cookie.getValue());
                }
            }
        }
        model.setAttribute("saveId", saveId);
        String viewName = "/member/login";
        return viewName;
    }
}
