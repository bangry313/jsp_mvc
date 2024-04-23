package com.ezen.mall.web.member.controller;

import com.ezen.mall.domain.member.service.MemberService;
import com.ezen.mall.web.common.core.handler.APIHandler;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class MemberIdDupCheckHandler implements APIHandler {

    private MemberService memberService = new MemberService();

    @Override
    public void process(Map<String, String> paramMap, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        String inputId = paramMap.get("id");
        boolean validated = memberService.getDuplicatedId(inputId);

        try {
            out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result", validated);// 사용가능한 아이디 의미
            if(validated) {
                jsonObject.put("message", "사용 가능한 아이디 입니다.");
            }else {
                jsonObject.put("message", "딴애가 쓰고 있는 아이디입니다.");
            }
            out.println(jsonObject.toString()); // Ojbect -> String(json)
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(out != null) out.close();
        }
    }
}
