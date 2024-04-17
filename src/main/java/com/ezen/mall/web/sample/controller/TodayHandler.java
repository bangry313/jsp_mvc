package com.ezen.mall.web.sample.controller;

import com.ezen.mall.web.common.core.controller.ControllerV4;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Map;

/**
 * /mvc/sample/today 요청에 대한 세부 컨트롤러(이벤트 리스너라 가정)
 */
public class TodayHandler implements Handler {
    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewPath = "/sample/today"; // 논리적 뷰 경로
        // 요청 파라미터 읽기
        String id = paramMap.get("id");

        Calendar calendar = Calendar.getInstance();
        String formattedToday = String.format("%1$tF %1$tT", calendar);
        model.setAttribute("today", formattedToday);
        model.setAttribute("id", id);
        return viewPath;
    }
}
