<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
%>
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
    <jsp:include page="/WEB-INF/views/fragment/sidebar.jsp" />
    <!-- Sidebar End -->

    <!-- Page Content Start -->
    <div id="page-content-wrapper">
      
      <!-- Nav Start -->
      <jsp:include page="/WEB-INF/views/fragment/nav.jsp" />
      <!-- Nav End -->

      <!-- Contents Start -->
      <div class="container-fluid">
        <h3 class="mt-4">게시글 상세</h3>
        <hr>
        <div class="form-group">
          <label>제목</label>
          <input type="text" class="form-control" value="${article.title}" readonly>
        </div>
        <div class="form-group">
          <label>내용</label>
          <textarea class="form-control" rows="5" readonly>${article.content}</textarea>
        </div>
        <div class="form-group">
          <label>작성자</label>
          <input type="text" class="form-control" name="writer" value="${article.writer}" readonly>
        </div>
        <div class="form-group">
          <label>등록일자</label>
          <input type="text" class="form-control" name="regDate" value="${article.regdate}" readonly>
        </div>

        <a href="register.html" class="btn btn-primary">댓글쓰기</a>
        <a href="list" class="btn btn-info">목록</a>
        <a href="#" class="btn btn-warning">수정</a>


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
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>
</body>
</html>
