<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex36 SpringJpa #04</title>
	</head>
	<body>
		<%
			out.println("Spring JPA #04 - Select Like Paging");
		%>
		<br><p>
		
		총글의 갯수 : ${totalElements} <br>
		총 페이지 : ${totalPages} <br>
		size : ${size}<br>
		pageNumber : ${pageNumber}<br>
		numberOfElements : ${numberOfElements}
		<hr>
		<c:forEach items="${members}" var="member">
		아이디 : ${member.id}<br>
		이름 : ${member.name}<br>
		이메일 : ${member.email}
		<hr>
		</c:forEach>
	</body>
</html>