package com.ezen.mall.web.common.core.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ezen.mall.web.sample.controller.HelloControllerV1;

/**
 * 프론트 컨트롤러 서블릿 구현
 * 웹 클라이언트의 요청에 대한 단일 진입점 역할 (메인 컨트롤러)
 */
//@WebServlet(name="/frontController", urlPatterns = {"/mvc/*"})
 public class FrontControllerServletV1 extends HttpServlet {

	private Map<String, ControllerV1> controllerMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		String contextPath = getServletContext().getContextPath();
		// 웹 클라이언트 요청에 대한 세부 컨트롤러 등록
		controllerMap.put(contextPath + "/mvc/sample/hello", new HelloControllerV1());
	}

	/** 요청 방식에 상관없이 웹 클라이언트 모든 요청 처리 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		// 웹 클라이언트 요청 URI 분석
		String requestURI = request.getRequestURI();
		System.out.println("[debug] : 웹 클라이언트 요청 URI - " + requestURI);
		
		// 웹 클라이언트 요청 URI에 대한 세부 컨트롤러 선택
		ControllerV1 controller = controllerMap.get(requestURI);
		// 요청한 세부 컨트롤러가 존재하지 않을 경우 응답코드로 404 전송
		if (controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		// 세부 컨트롤러 호출(실행)
		controller.process(request, response);
	}
}
