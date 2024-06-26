<%--
  네비게이션 메뉴
  User: Si7D-08
  Date: 2024-03-29
  Time: PM 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
    <button class="btn btn-primary" id="menu-toggle">Toggle</button>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
            <li class="nav-item active">
                <a class="nav-link" href="#">홈 <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">메뉴1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">메뉴2</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    게시판
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                    <c:forEach var="board" items="${boardList}">
                        <a class="dropdown-item" href="/mvc/board/list?boardId=${board.boardId}">${board.title}</a>
                    </c:forEach>
                </div>
            </li>

            <c:choose>
                <c:when test="${empty loginMember}">
                    <li class="nav-item">
                        <a class="btn btn-outline-primary" href="/mvc/member/register">회원가입</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-primary" href="/mvc/member/login">로그인</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="nav-item">
                        <a class="btn btn-outline-primary">${loginMember.name}님 로그인중</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-danger" href="/mvc/member/logout-action">로그아웃</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</nav>