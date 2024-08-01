<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex19 MyBatis Complex</title>
	</head>
	<body>
		<%
			out.println("MyBatis 사용하기 : Hello World");
		%>
		<br>
			<c:forEach items="${employees}" var="dto">
			${dto.ename1} / ${dto.dno1} / ${dto.dname1}<br>
			</c:forEach>
	</body>
</html>