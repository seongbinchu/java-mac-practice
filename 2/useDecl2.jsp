<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%!
	public int add(int a,int b){
		int c = a+b;
		return c;
	}
	public int subtract(int a , int b){
		int c= a-b;
		return c;
	}
%>
<html>
<head>
<meta charset="EUC-KR">
<title>스크립트릿에서 선언부 사용하기</title>
</head>
<body>
<%
	int value1 =3;
	int value2 =9;
	
	int addResult=add(value1,value2);
	int subtractResult=subtract(value1,value2);
%>
<%=value1 %>+<%=value2 %>=<%=addResult %>
<br>
<%=value2 %>-<%=value2 %>=<%=subtractResult %>
</body>
</html>