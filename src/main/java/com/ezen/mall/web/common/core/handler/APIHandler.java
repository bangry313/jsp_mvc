package com.ezen.mall.web.common.core.handler;
import com.ezen.mall.web.common.core.controller.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface APIHandler {
	public void process(Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response);
}


