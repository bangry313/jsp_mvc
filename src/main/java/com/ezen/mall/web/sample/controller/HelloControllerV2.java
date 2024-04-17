package com.ezen.mall.web.sample.controller;

import com.ezen.mall.web.common.core.controller.ControllerV2;
import com.ezen.mall.web.common.core.view.JSPView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloControllerV2 implements ControllerV2 {
	@Override
	public JSPView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = "MVC 디자인 패턴을 적용한 웹 애플리케이션 개발 V2";
		// JSP에서 message를 출력할 수 있도록 request 스코프 객체에 message 설정
		request.setAttribute("message", message);
		return new JSPView("/WEB-INF/views/sample/hello.jsp");
	}
}
