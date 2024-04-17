package com.ezen.mall.web.common.core.controller;

import com.ezen.mall.web.common.core.view.JSPView;
import com.ezen.mall.web.sample.controller.HelloControllerV3;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 프론트 컨트롤러 서블릿 구현
 * 웹 클라이언트의 요청에 대한 단일 진입점 역할 (메인 컨트롤러)
 */
//@WebServlet(name = "/frontController", urlPatterns = { "/mvc/*" })
public class FrontControllerServletV3 extends HttpServlet {

	private Map<String, ControllerV3> controllerMap = new HashMap<>();
	private ViewResolver viewResolver;

	@Override
	public void init() throws ServletException {
		String contextPath = getServletContext().getContextPath();
		// 웹 클라이언트 요청에 대한 세부 컨트롤러 등록
		controllerMap.put(contextPath + "/mvc/sample/hello", new HelloControllerV3());
		viewResolver = new ViewResolver();
	}

	/** 요청 방식에 상관없이 웹 클라이언트 모든 요청 처리 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 웹 클라이언트 요청 URI 분석
		String requestURI = request.getRequestURI();
		System.out.println("[debug] : 웹 클라이언트 요청 URI - " + requestURI);

		// 웹 클라이언트 요청 URI에 대한 세부 컨트롤러 선택
		ControllerV3 controller = controllerMap.get(requestURI);
		if (controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		// 웹 클라이언트 요청파라메터들을 맵에 저장
		Map<String, String> paramMap = createParamMap(request);
		// 세부 컨트롤러 호출(실행)
		ModelAndView mav = controller.process(paramMap);
		// 논리적 뷰 이름 반환
		String logicalViewName = mav.getViewName();
		// 뷰 선택
		JSPView view = viewResolver.resolve(logicalViewName);
		view.render(mav.getModel(), request, response);
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		Map<String, String> paramMap = new HashMap<>();
		Enumeration<String> e = request.getParameterNames();
		Iterator<String> iter = e.asIterator();
		while(iter.hasNext()) {
			String paramName = iter.next();
			String paramValue = request.getParameter(paramName);
			paramMap.put(paramName, paramValue);
		}
		return paramMap;
	}
}
