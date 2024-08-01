<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex06 Model</title>
	</head>
	<body>
		<%
		    out.println("Model : Hello World");
		%>
		<br>
		당신의 이름은 ${name} 입니다.	<!-- EL표기법 -->
	</body>
</html>