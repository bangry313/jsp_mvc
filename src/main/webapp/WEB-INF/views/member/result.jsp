<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
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

        <div class="bg-pattern text-primary text-opacity-50 opacity-25 w-100 h-100 start-0 top-0 position-absolute">
        </div>
        <div class="bg-gradientwhite flip-y w-50 h-100 start-50 top-0 translate-middle-x position-absolute"></div>
        <div class="container pt-11 pt-lg-14 pb-9 position-relative z-1">
          <div class="row align-items-center justify-content-center">
            <div class="col-md-6 col-sm-8 mt-3">
              <h3>회원 가입을 축하합니다.</h3>
              <p class="mb-2 w-lg-75">
                가입하신 정보는 아래와 같습니다.
              </p>

              <div class="width-50x pt-1 bg-primary mb-3"></div>

              <div class="position-relative">
                <div>
                    <div class="input-icon-group mb-3">
                      <span class="input-icon">
                        <i class="bx bx-user-pin"></i>
                      </span>
                      <input type="text" class="form-control" disabled value="${member.id}">
                    </div>

                    <div class="input-icon-group mb-3">
                      <span class="input-icon">
                        <i class="bx bx-user"></i>
                      </span>
                      <input type="text" class="form-control" disabled value="${member.name}">
                    </div>

                    <div class="input-icon-group mb-3">
                      <span class="input-icon">
                        <i class="bx bx-envelope"></i>
                      </span>
                      </span>
                      <input type="text" class="form-control" disabled value="${member.email}">
                    </div>

                    <div class="input-icon-group mb-3">
                      <span class="input-icon">
                        <i class="bx bx-lock-open"></i>
                      </span>
                      </span>
                      <input type="text" class="form-control" disabled value="${member.regdate}">
                    </div>

                    <div class="d-grid">
                      <a class="btn btn-primary" href="/">홈으로</a>
                    </div>
                </div>
              </div>
            </div>
          </div>
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
    $("#menu-toggle").click(function(e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>
</body>
</html>
