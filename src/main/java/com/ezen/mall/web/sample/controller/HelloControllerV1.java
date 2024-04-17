package com.ezen.mall.web.sample.controller;

import com.ezen.mall.web.common.core.controller.ControllerV1;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloControllerV1 implements ControllerV1 {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Service 객체로부터 반환받은 데이터라 가정
//		String message = someService.getMessage();
		String message = "MVC 디자인 패턴을 적용한 웹 애플리케이션 개발 V1";
		
		// JSP에서 message를 출력할 수 있도록 request 스코프 객체에 message 설정
		request.setAttribute("message", message);
		// 화면에 렌더링을 담당하는 JSP로 포워드
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views/sample/hello.jsp");
		requestDispatcher.forward(request, response);
	}
}
