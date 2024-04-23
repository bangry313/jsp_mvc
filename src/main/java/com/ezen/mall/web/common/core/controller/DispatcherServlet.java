package com.ezen.mall.web.common.core.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.mall.web.common.core.adapter.HandlerAdapter;
import com.ezen.mall.web.common.core.adapter.RequestAPIHandlerAdapter;
import com.ezen.mall.web.common.core.adapter.RequestMappingController3Adapter;
import com.ezen.mall.web.common.core.adapter.RequestMappingHandlerAdapter;
import com.ezen.mall.web.common.core.view.JSPView;

/**
 * MVC 디자인 패턴 + 프론트 컨트롤러 패턴 + 어댑터 패턴 적용 서블릿 구현
 * 웹 클라이언트의 요청에 대한 단일 진입점 역할 (메인 컨트롤러)
 */
@WebServlet(name = "/frontController", urlPatterns = { "/mvc/*" })
public class DispatcherServlet extends HttpServlet {

	private final Map<String, Object> handlerMappingMap = new HashMap<>();
	private final List<HandlerAdapter> handlerAdapters = new ArrayList<>();
	private ViewResolver viewResolver;

	@Override
	public void init() throws ServletException {
		initHandlerMappingMap();
		initHandlerAdapters();
		viewResolver = new ViewResolver();
	}


	/**
	 * 세부 컨트롤러 생성 및 등록
	 */
	private void initHandlerMappingMap() {
		String contextPath = getServletContext().getContextPath();
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream("/config/request-mapping.properties");
		try {
			prop.load(in);
			Set<Object> keys = prop.keySet();
			Iterator<Object> iter = keys.iterator();
			while (iter.hasNext()) {
				String requestURL = (String)iter.next();
				String handlerClass = prop.getProperty(requestURL);
				Object handler = Class.forName(handlerClass).newInstance();
				handlerMappingMap.put(contextPath + requestURL, handler);
				System.out.println("[debug] : 등록된 세부 컨트롤러 목록");
				System.out.println(handlerMappingMap);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * HandlerAdapter 생성 및 등록
	 */
	private void initHandlerAdapters() {
		handlerAdapters.add(new RequestMappingHandlerAdapter());
		handlerAdapters.add(new RequestMappingController3Adapter());
		handlerAdapters.add(new RequestAPIHandlerAdapter());
	}

	/** 요청 방식에 상관없이 웹 클라이언트 모든 요청 처리 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 웹 클라이언트 요청에 대한 세부 컨트롤러 선택
		Object handler = getHandler(request);

		if (handler == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// 핸들러어댑터 선택
		HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
		if(handlerAdapter == null) {
			throw new IllegalArgumentException("[error] : HandlerAdapter를 찾을 수 없습니다. Handler = " + handler);
		}
		ModelAndView mav = handlerAdapter.handle(request, response, handler);
		if(mav == null){
			return;
		}
		// 뷰 실행
		processView(request, response, mav);
	}

	private Object getHandler(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		return handlerMappingMap.get(requestURI);
	}

	private HandlerAdapter getHandlerAdapter(Object handler) {
		for (HandlerAdapter adapter : handlerAdapters) {
			if (adapter.supports(handler)) {
				return adapter;
			}
		}
		return null;
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
		while (iter.hasNext()) {
			String paramName = iter.next();
			String paramValue = request.getParameter(paramName);
			paramMap.put(paramName, paramValue);
		}
		return paramMap;
	}

	private void processView(HttpServletRequest request, HttpServletResponse response, ModelAndView mav) throws IOException, ServletException {
		// 뷰 선택
		String viewName = mav.getViewName();
		if(viewName.startsWith("redirect:")) {
			// 리다이렉트
			response.sendRedirect(viewName.substring(viewName.indexOf(":")+1));
		}else {
			// 포워드
			JSPView view = viewResolver.resolve(mav.getViewName());
			view.render(new Model(mav.getModel()), request, response);
		}
	}
}
