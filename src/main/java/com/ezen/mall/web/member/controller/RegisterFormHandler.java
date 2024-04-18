package com.ezen.mall.web.member.controller;

import com.ezen.mall.domain.member.dto.Member;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * /mvc/member/register 요청에 대한 핸들러 
 * 회원가입 화면 요청
 */
public class RegisterFormHandler implements Handler {
    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "/member/register";
        return viewName;
    }
}
