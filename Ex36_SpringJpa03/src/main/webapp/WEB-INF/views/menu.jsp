<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex36 SpringJpa #03</title>
	</head>
	<body>
		<%
			out.println("Spring JPA #03");
		%>
		<br><p>
		<!-- 게시판 처럼 링크를 만듬. -->
		<a href=/selectByNameLike?name=test&page=1>Name Like 조회 : 1페이지</a> <br><p>
		<a href=/selectByNameLike?name=test&page=2>Name Like 조회 : 2페이지</a> <br><p>
		<a href=/selectByNameLike?name=test&page=3>Name Like 조회 : 3페이지</a> <br><p>
		<hr>
	</body>
</html>