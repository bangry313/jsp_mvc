package com.ezen.mall.web.common.core.listener;

import com.ezen.mall.domain.member.dto.Member;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class MemberLogingListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (event.getName().equals("loginMember")) {
            Member member = (Member)event.getValue();
            System.out.println("[Info] : " + member.getName() + "님 로그인하셨습니다.");
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        if (event.getName().equals("loginMember")) {
            Member member = (Member)event.getValue();
            System.out.println("[Info] : " + member.getName() + "님 로그아웃하셨습니다.");
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {}
}
