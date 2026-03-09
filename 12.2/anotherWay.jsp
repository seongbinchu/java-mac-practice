<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>anotherWay.jsp</title>
</head>
<body>
	<table border ="1">
		<tr>
			<td>1~100까지</td>
		</tr>
		<tr>
			<td>1</td>
		</tr>
		<tr>
			<td>2</td>
		</tr>
		
	</table>
	
	<table border ="1">
	<%
		for(int i=0;i<=100;i++){
	%>
	<tr>
			<td><%=i%></td>
	</tr>
	<%
		}
	%>
	</table>
		
</body>
</html>