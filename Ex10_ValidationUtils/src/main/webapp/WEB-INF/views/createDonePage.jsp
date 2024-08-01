<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>CreatePageDone</title>
	</head>
	<body>
		createDonePage.jsp
		<br />
		<!--  커맨드 객체의 속성을 이용한 값을 출력한다. -->
		이름 : ${dto.writer} <br />
		내용 : ${dto.content}
	</body>
</html>