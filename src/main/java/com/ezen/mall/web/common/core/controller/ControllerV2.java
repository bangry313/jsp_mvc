package com.ezen.mall.web.common.core.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ezen.mall.web.common.core.view.JSPView;

/**
 * 세부 컨트롤러 클래스 작성을 위한 인터페이스(명세)
 * @author 김기정
 */
public interface ControllerV2 {
	public JSPView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}


