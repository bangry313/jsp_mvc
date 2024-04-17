package com.ezen.mall.web.common.core.handler;
import com.ezen.mall.web.common.core.controller.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface Handler {
	public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response);
}


