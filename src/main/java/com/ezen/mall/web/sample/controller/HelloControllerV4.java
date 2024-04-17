package com.ezen.mall.web.sample.controller;

import com.ezen.mall.web.common.core.controller.ControllerV4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class HelloControllerV4 implements ControllerV4 {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		String message = "MVC 디자인 패턴을 적용한 웹 애플리케이션 개발 V4";
		model.put("message", message);
		return "/sample/hello";
	}
}
