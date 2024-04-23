package com.ezen.mall.domain.member.service;

import com.ezen.mall.domain.member.dao.JdbcMemberDao;
import com.ezen.mall.domain.member.dao.MemberDao;
import com.ezen.mall.domain.member.dto.Member;

import java.sql.SQLException;

/**
 * 회원 관련한 비즈니스 메소드 정의
 */
public class MemberService {

    private MemberDao memberDao;

    public MemberService() {
        memberDao = new JdbcMemberDao();
    }

    // 회원가입
    public Member registerMember(Member member){
        try {
            memberDao.create(member);
            return memberDao.findById(member.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 로그인(회원인증)
    public Member login(String id, String passwd){
        Member loginMember = null;
        boolean isMember = false;
        try {
            isMember = memberDao.findByIdNPasswd(id, passwd);
            if(isMember){
                loginMember = memberDao.findById(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  loginMember;
    }

    public boolean getDuplicatedId(String inputId){
        boolean validated = false;
        try {
            if(!memberDao.findCheckId(inputId)){
                validated = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return validated;
    }

    // 기타 등등

    public static void main(String[] args) throws SQLException {
        MemberService memberService = new MemberService();
        Member loginMember = memberService.login("bangry", "1111");
        System.out.println(loginMember);
    }
}
