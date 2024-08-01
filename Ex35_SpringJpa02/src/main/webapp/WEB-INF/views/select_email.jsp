<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex34 SpringJpa #02</title>
	</head>
	<body>
		<%
			out.println("Spring JPA #02 - Select By Email");
		%>
		<br>
		
		아이디 : ${member.id}<br>
		이름 : ${member.name}<br>
		이메일 : ${member.email}
	</body>
</html>