package com.ezen.mall.web.common.core.adapter;

import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.controller.ModelAndView;
import com.ezen.mall.web.common.core.handler.APIHandler;
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

public class RequestAPIHandlerAdapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		return handler instanceof APIHandler;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws ServletException, IOException {
		APIHandler handlerObject = (APIHandler) handler;
		Map<String, String> paramMap = createParamMap(request);
		handlerObject.process(paramMap, request, response);
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
}
