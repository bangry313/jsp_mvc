package com.ezen.mall.web.common.core.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface ControllerV4 {
	public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response);
}


