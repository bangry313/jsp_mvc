<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    String message = xxxxService.getMessage();
    String message = "Model1 개발 방식";
    request.setAttribute("message", message);
%>

<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
${message}

</body>
</html>
