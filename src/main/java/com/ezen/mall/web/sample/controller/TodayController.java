package com.ezen.mall.web.sample.controller;

import com.ezen.mall.web.common.core.controller.*;
import com.ezen.mall.web.common.core.view.JSPView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

/**
 * /mvc/sample/today 요청에 대한 세부 컨트롤러(이벤트 리스너라 가정)
 */
public class TodayController implements ControllerV3 {
    @Override
    public ModelAndView process(Map<String, String> paramMap) {
        String viewPath = "/sample/today2"; // 논리적 뷰 경로
        // 요청 파라미터 읽기
        String id = paramMap.get("id");

        Calendar calendar = Calendar.getInstance();
        String formattedToday = String.format("%1$tF %1$tT", calendar);

        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewPath);
        mav.setAttribute("today", formattedToday);
        mav.setAttribute("id", id);
        return mav;
    }
}
