package com.ezen.mall.web.board.controller;

import com.ezen.mall.domain.member.dto.Member;
import com.ezen.mall.web.common.core.controller.Model;
import com.ezen.mall.web.common.core.handler.Handler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * /mvc/board/register 요청에 대한 핸들러
 * 게시글 쓰기 화면
 */
public class ArticleRegisterHandler implements Handler {
    @Override
    public String process(Map<String, String> paramMap, Model model, HttpServletRequest request, HttpServletResponse response) {
        String viewName = "/board/register";
        Member loginMember = null;
        // 로그인 사용자만이 보여주는 페이지
        HttpSession session = request.getSession(false);
        if(session != null){
            loginMember = (Member) session.getAttribute("loginMember");
        }
        if(loginMember == null){
            model.setAttribute("message", "게시판 글쓰기는 로그인 사용자만 가능합니다.");
            model.setAttribute("referer", "/mvc/board/register");
            viewName = "/member/login";
        }
        return viewName;
    }
}
