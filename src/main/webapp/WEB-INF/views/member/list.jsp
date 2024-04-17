<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>회원목록</title>
</head>
<body>
<h2>회원목록</h2>
<table border="1">
    <tr>
        <th>아이디</th><th>이름</th><th>이메일</th>
    </tr>
    <c:forEach items="${list}" var="member">
        <tr>
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.email}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>










