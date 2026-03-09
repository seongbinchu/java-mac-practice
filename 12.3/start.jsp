<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>start.jsp</title>
</head>
<body>
	<form action="result.jsp"method="get">
		userInput <input type="text" name="userInput">
		otherInput <input type="text" name="otherInput">
		<input type="submit">
	</form>
	<a href="result.jsp?userInput=test&otherInput=some">go result</a>
</body>
</html>