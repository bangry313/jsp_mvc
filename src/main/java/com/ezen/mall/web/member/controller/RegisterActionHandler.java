package com.ezen.mall.web.member.controller;

import com.ezen.mall.domain.member.dto.Member;
import com.ezen.mall.domain.member.service.MemberService;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;
import com.ezen.mall.web.common.validate.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
public class RegisterActionHandler implements Handler {

    private MemberService memberService = new MemberService();

    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "redirect:/mvc/member/result";

        String id = paramMap.get("id");
        String name = paramMap.get("name");
        String email = paramMap.get("email");
        String passwd = paramMap.get("passwd");

        // 입력데이터 데이터 유효성 검증
        Map<String, String> errors = new HashMap<>();
        if(Validator.isEmpty(id)) {
            errors.put("id", "[서버]아이디는 필수 입력사항입니다.");
        }
        if(Validator.isEmpty(name)) {
            errors.put("name", "[서버]이름은 필수 입력사항입니다.");
        }
        if(Validator.isEmpty(email)) {
            errors.put("email", "[서버]이메일은 필수 입력사항입니다.");
        }
        if(Validator.isEmpty(passwd)) {
            errors.put("passwd", "[서버]비밀번호는 필수 입력사항입니다.");
        }

        // 검증 오류 메시지가 한개라도 존재 시 회원가입 화면으로 포워드
        if(!errors.isEmpty()) {
            model.setAttribute("errors", errors);
            viewName = "/member/register";
        }
        // 회원 가입 처리
        Member member = new Member(id, passwd, name, email, "default.jpg");
        member = memberService.registerMember(member);

        // 회원 가입 정보를 세션에 저장
        HttpSession session = request.getSession();
        session.setAttribute("member", member);
        return viewName;
    }
}
