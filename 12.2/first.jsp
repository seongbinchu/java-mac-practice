<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	Hello JSP
	http://localhost:8080/12_02/first.jsp
	톰캣위치정보          루트  요청데이터
	
	url 요청 => 코드 던져줌 => 브라우저가 실행해서 출력
	jsp 서버에서 실행해서 값 비쥬얼적으로 html으로 출력
	
	
	http 프로토콜
	
	clint request
	request line	=> 전송방식,프로토콜(버전),url
	request header  => 클라이언트 정보(브라우저,os,인코딩,랭귀지)
	request body	=> 파라미터
	
	=> server response
	response line	=> 프로토콜(버전),응답코드
	response header	=> 서버정보
	response body	=> 응답결과 ex(html 코드)
	
	웹브라우저	=> HTTP 요청[TEXT]		WAS		=>실행	jsp
				<= HTTP 응답전송[TEXT]
</body>
</html>