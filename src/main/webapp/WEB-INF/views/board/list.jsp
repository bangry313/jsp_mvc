<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url var="register" value="register">
    <c:param name="boardId" value="${param.boardId}"/>
</c:url>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>JSP 실습</title>
    <!-- Bootstrap core CSS -->
    <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/simple-sidebar.css" rel="stylesheet">
</head>

<body>

<div class="d-flex" id="wrapper">

    <!-- Sidebar Start -->
    <jsp:include page="/WEB-INF/views/fragment/sidebar.jsp"/>
    <!-- Sidebar End -->

    <!-- Page Content Start -->
    <div id="page-content-wrapper">

        <!-- Nav Start -->
        <jsp:include page="/WEB-INF/views/fragment/nav.jsp"/>
        <!-- Nav End -->

        <!-- Contents Start -->
        <div class="container-fluid">

            <h3 class="mt-4">자유게시판 목록
                <span>
            <a href="${register}" class="btn btn-sm btn-success">게시글 쓰기</a>
          </span>
            </h3>

            <hr>

            <form id="searchForm">
                <div class="input-group">
                    <input type="hidden" name="page" value="1">
                    <div class="input-group-prepend">
                        <select class="custom-select" name="type">
                            <option selected value="">----- 검색유형 -----</option>
                            <option value="t">제목</option>
                            <option value="c">내용</option>
                            <option value="w">작성자</option>
                            <option value="tc">제목 + 내용</option>
                            <option value="tcw">제목 + 내용 + 작성자</option>
                        </select>
                    </div>
                    <input type="search" class="form-control" name="value">
                    <div class="input-group-append" id="button-addon4">
                        <button class="btn btn-success btn-search" type="submit">검색</button>
                    </div>
                </div>
            </form>

            <table class="table table-sm table-striped">
                <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>등록일자</th>
                </tr>
                </thead>

                <tbody>
                <c:choose>
                    <c:when test="${empty list}">
                        <tr>
                            <td colspan="5" style="color: red; text-align: center">게시글이 존재하지 않습니다.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="article" items="${list}" varStatus="loop">
                            <tr>
                                <td>${pagination.params.rowCount - ( (pagination.params.requestPage - 1) * pagination.params.elementSize) - loop.index}</td>
                                <td>
                                    <c:forEach begin="1" end="${article.levelNo}">
                                        <img src="/img/level.gif" style="width: 10px; height: 10px;">
                                    </c:forEach>
                                    <c:if test="${article.levelNo != 0}">
                                        <img src="/img/re.gif">
                                    </c:if>
                                    <a href="read?boardId=${article.boardId}&articleId=${article.articleId}">${article.title}</a>
                                </td>
                                <td>${article.writer}</td>
                                <td>${article.hitCount}</td>
                                <td>${article.regdate}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>

            <ul class="pagination h-100 justify-content-center align-items-center">

                <c:if test="${pagination.showFirst}">
                    <li class="page-item"><a class="page-link" href="?page=1&type=${param.type}&value=${param.value}">처음으로</a>
                    </li>
                </c:if>

                <c:if test="${pagination.showPrevious}">
                    <li class="page-item"><a class="page-link"
                                             href="?page=${pagination.previousStartPage}&type=${param.type}&value=${param.value}">이전목록</a>
                    </li>
                </c:if>

                <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}">
                    <c:url var="list" value="list" scope="request">
                        <c:param name="page" value="${i}"/>
                        <c:param name="type" value="${param.type}"/>
                        <c:param name="value" value="${param.value}"/>
                    </c:url>
                    <li class="page-item <c:if test="${i == pagination.params.requestPage}">active</c:if>"><a
                            class="page-link" href="${list}">${i}</a></li>
                </c:forEach>

                <c:if test="${pagination.showNext}">
                    <li class="page-item"><a class="page-link"
                                             href="?page=${pagination.nextStartPage}&type=${param.type}&value=${param.value}">다음목록</a>
                    </li>
                </c:if>
                <c:if test="${pagination.showLast}">
                    <li class="page-item"><a class="page-link"
                                             href="?page=${pagination.totalPages}&type=${param.type}&value=${param.value}">끝으로</a>
                    </li>
                </c:if>
            </ul>
        </div>

    </div>
    <!-- Contents Start -->

</div>
</div>
<!-- Wrapper End -->

<!-- Bootstrap core JavaScript -->
<script src="/vendor/jquery/jquery.min.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function (e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>
</body>
</html>
