package com.ezen.mall.web.common.core.adapter;

import com.ezen.mall.web.common.core.controller.ControllerV3;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.controller.ModelAndView;
import com.ezen.mall.web.common.core.handler.Handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RequestMappingController3Adapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return handler instanceof ControllerV3;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {
		ControllerV3 controllerV3 = (ControllerV3) handler;
		Map<String, String> paramMap = createParamMap(request);
		ModelAndView mav = controllerV3.process(paramMap);
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
