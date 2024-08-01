<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex14 SimpleBBS</title>
	</head>
	<body>
		내용보기 <br>
		<hr>
		작성자 : ${dto.writer} <br>
		제목 : ${dto.title} <br>
		내용 : ${dto.content} 
		<hr>
		
		<br><p>
		<a href="list">목록보기</a>
	</body>
</html>