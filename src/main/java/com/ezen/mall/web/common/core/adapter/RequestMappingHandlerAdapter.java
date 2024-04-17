package com.ezen.mall.web.common.core.adapter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ezen.mall.web.common.core.controller.ControllerV4;
import com.ezen.mall.web.common.core.controller.ModelAndView;
import com.ezen.mall.web.common.core.handler.Handler;

public class RequestMappingHandlerAdapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return handler instanceof Handler;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {
		Handler handlerObject = (Handler) handler;
		Map<String, String> paramMap = createParamMap(request);
		Map<String, Object> model = new HashMap<>();
		String viewName = handlerObject.process(paramMap, model, request, response);
		ModelAndView mav = new ModelAndView(viewName);
		mav.setModel(model);
		return mav;
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
}
