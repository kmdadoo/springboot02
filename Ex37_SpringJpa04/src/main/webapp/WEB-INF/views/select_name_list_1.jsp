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
			out.println("Spring JPA #04 - Name Like");
		%>
		<br><p>
		
		<hr>
		<c:forEach items="${members}" var="member">
		아이디 : ${member.id}<br>
		이름 : ${member.name}<br>
		이메일 : ${member.email}
		<hr>
		</c:forEach>
	</body>
</html>