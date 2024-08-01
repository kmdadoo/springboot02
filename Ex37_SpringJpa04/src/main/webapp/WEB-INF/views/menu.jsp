<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex37 SpringJpa #04</title>
	</head>
	<body>
		<%
			out.println("Spring JPA #04");
		%>
		<br><p>
		<a href=/selectNameLike1?name=test>Name Like 조회 : JQL 1</a> <br><p>
		<a href=/selectNameLike2?name=test>Name Like 조회 : JQL 2</a> <br><p>
		<a href=/selectNameLike3?name=test&page=2>Name Like 조회 : JQL 3 - 2페이지</a> <br><p>
		<a href=/selectNameLike4?name=test>Name Like 조회 : Native SQL</a> <br><p>
	</body>
</html>