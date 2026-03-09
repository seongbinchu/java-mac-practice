<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>write.jsp</title>
</head>
<body>
	<h1>write.jsp</h1>
	<hr>
		<a href="list.jsp">목록으로</a>
	<hr>
	<form action="doWrite.jsp" method="post">
		작성자 : <input type="text" name="writer" >
		<br>
		코멘트 : <input type="text" name="comment" >
		<br>
		<input type="submit">
	</form>
</body>
</html>