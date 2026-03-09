<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>자바식</title>
</head>
<body>
	<%
		int sum=0;
		for(int i =0;i<=10;i++){
			sum+=i;
		}
	%>
	1부터10까지의 합<%= sum %>
</body>
</html>