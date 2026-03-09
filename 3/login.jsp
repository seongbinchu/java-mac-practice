<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%
	String id = request.getParameter("memberId");
if(id!=null&&id.equals("madvirus")){
	response.sendRedirect("idnex.jsp");
}else{
%>
<html>
<head>
<meta charset="EUC-KR">
<title>로그인에 실패</title>
</head>
<body>
잘못된 아이디입니다.

<%--jsp주석 --%>
<!-- html주석 -->
</body>
</html>
<%
	}
%>