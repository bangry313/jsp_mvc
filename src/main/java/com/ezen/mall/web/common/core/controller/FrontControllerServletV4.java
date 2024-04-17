package com.ezen.mall.web.common.core.controller;

import com.ezen.mall.web.common.core.view.JSPView;
import com.ezen.mall.web.sample.controller.HelloControllerV4;
import com.ezen.mall.web.sample.controller.TodayController;

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

//@WebServlet(name = "/frontController", urlPatterns = { "/mvc/*" })
public class FrontControllerServletV4 extends HttpServlet {

	private Map<String, ControllerV4> controllerMap = new HashMap<>();
	private ViewResolver viewResolver;

	@Override
	public void init() throws ServletException {
		String contextPath = getServletContext().getContextPath();
		controllerMap.put(contextPath + "/mvc/sample/hello", new HelloControllerV4());
		//controllerMap.put(contextPath + "/mvc/sample/today", new TodayController());
		viewResolver = new ViewResolver();
	}

	/** 요청 방식에 상관없이 웹 클라이언트 모든 요청 처리 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		System.out.println("[debug] : 웹 클라이언트 요청 URI - " + requestURI);

		// 웹 클라이언트 요청 URI에 대한 세부 컨트롤러 선택
		ControllerV4 controller = controllerMap.get(requestURI);
		if (controller == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		// 웹 클라이언트 요청파라메터들을 맵에 저장
		Map<String, String> paramMap = createParamMap(request);
		// 모델 생성
		Model model = new Model();
		// 세부 컨트롤러 호출(실행) JSP 논리적 이름 반환
		String logicalViewName = controller.process(paramMap, model, request, response);
		// 뷰 선택
		JSPView view = viewResolver.resolve(logicalViewName);
		view.render(model, request, response);
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		Map<String, String> paramMap = new HashMap<String, String>();
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
