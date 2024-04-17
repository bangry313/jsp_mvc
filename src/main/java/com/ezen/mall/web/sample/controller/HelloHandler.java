package com.ezen.mall.web.sample.controller;

import com.ezen.mall.web.common.core.controller.ControllerV4;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HelloHandler implements Handler {
	@Override
	public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
		String message = "MVC 디자인 패턴을 적용한 웹 애플리케이션 개발 V5";
		model.setAttribute("message", message);
//		return "/sample/hello"; // 포워드
		return "redirect:https://www.naver.com"; // 리다이렉트
	}
}
