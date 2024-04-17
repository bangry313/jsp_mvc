package com.ezen.mall.web.member.controller;

import com.ezen.mall.domain.member.dto.Member;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * /mvc/member/list 요청에 대한 핸들러
 * 회원 목록 처리
 */
public class ListHandler implements Handler {
    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "/member/list";
//        List< Member> list  = memberService.getMeMembers();
        List<Member> list = new ArrayList<>();
        list.add(new Member("bangry1", "1111", "김기정", "bangry@gmail.com"));
        list.add(new Member("bangry2", "1111", "김기정", "bangry@gmail.com"));
        list.add(new Member("bangry3", "1111", "김기정", "bangry@gmail.com"));
        list.add(new Member("bangry4", "1111", "김기정", "bangry@gmail.com"));
        list.add(new Member("bangry5", "1111", "김기정", "bangry@gmail.com"));
        model.setAttribute("list", list);
        return viewName;
    }
}
