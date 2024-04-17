package com.ezen.mall.web.common.core.view;

import com.ezen.mall.web.common.core.controller.Model;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JSP 경로를 받아 포워드 역할의 자바빈
 */
public class JSPView {
	private String viewPath;
	public JSPView(){
		this("/WEB-INF/views/index.jsp");
	}
	public JSPView(String viewPath) {
		this.viewPath = viewPath;
	}

	public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}

	public void render(Model model, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 모델의 속성을 HttpServletReqeust의 속성으로 변환
		convertModelToRequestAttribute(model, request);
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
		dispatcher.forward(request, response);
	}

	private void convertModelToRequestAttribute(Model model, HttpServletRequest request) {
		// Stream API와 람다식 활용
		model.getMap().forEach((key, value) -> request.setAttribute(key, value));
	}
}








