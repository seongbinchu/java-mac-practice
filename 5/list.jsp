<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>list.jsp</title>
</head>
<body>
	<%-- /data/comments.txt에 저장 --%>
	<h1>list.jsp</h1>
	<hr>
		<a href="write.jsp">코멘트 등록</a>
	<hr>
	<table border="1" align="center" width="90%">
		<caption>등록된 코멘트</caption>
		<thead>
			<tr>
				<th width="20%">작성자</th>
				<th>코멘트</th>
				<%-- 작성일은 2025.12.04 로 표현한다. --%>
				<th width="20%">작성일</th>
			</tr>
		</thead>		
	</table>
</body>
</html>















