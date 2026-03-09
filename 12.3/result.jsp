<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>result.jsp</title>
</head>
<body>
	<%
		request.setCharacterEncoding("euc-kr");
		String userInput = request.getParameter("userInput");
		
		userInput = new String(userInput.getBytes("8859_1"),"euc-kr");
		String otherInput = request.getParameter("otherInput");
	%>
	userInput : <%= userInput %>
	<br>
	otherInput : <%= otherInput %>
</body>
</html>