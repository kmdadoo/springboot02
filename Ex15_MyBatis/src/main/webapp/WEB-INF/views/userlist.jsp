<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex15 MyBatise</title>
	</head>
	<body>
		<%
		    out.println("MyBatis : Hello World");
		%>
		<br>
		
		<c:forEach var="dto" items="${users}">
		    ${dto.id} / ${dto.name}<br>
		</c:forEach>
	</body>
</html>