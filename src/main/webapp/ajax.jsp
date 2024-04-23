<%@ page import="netscape.javascript.JSObject"%><%@ page import="org.json.JSONObject"%><%@ page contentType="application/json;charset=UTF-8" language="java" %>
<%
  System.out.println("ajax 요청 수신.......");
  request.setCharacterEncoding("utf-8");
  String message = request.getParameter("message");
  System.out.println("수신한 메시지 : " + message);
//  Thread.sleep(5000);

JSONObject object = new JSONObject();
object.put("name", "김기정");
object.put("message", message);
String json = object.toString();
%>
<%= json %>
