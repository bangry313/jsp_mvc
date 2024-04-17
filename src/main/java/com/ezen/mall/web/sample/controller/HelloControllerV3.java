package com.ezen.mall.web.sample.controller;

import com.ezen.mall.web.common.core.controller.ControllerV3;
import com.ezen.mall.web.common.core.controller.ModelAndView;

import java.util.Map;

public class HelloControllerV3 implements ControllerV3 {
	@Override
	public ModelAndView process(Map<String, String> paramMap) {
		String message = "MVC 디자인 패턴을 적용한 웹 애플리케이션 개발 V3";
		// ModelAndView에 JSP의 논리적이름과 JSP에서 사용할 데이터 저장
		ModelAndView mav = new ModelAndView("/sample/hello");
		mav.setAttribute("message", message);
		return mav;
	}
}
