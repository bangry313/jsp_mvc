package com.ezen.mall.web.home.controller;

import com.ezen.mall.web.common.core.handler.Handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * /mvc/ 요청에 해당하는 요청 핸들러
 */
public class HomeHandler implements Handler {
	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		// ....
		return "/index";
	}
}
