package com.ezen.mall.web.member.controller;

import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class RegisterResultHandler implements Handler {
    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "/member/result";
        return viewName;
    }
}
