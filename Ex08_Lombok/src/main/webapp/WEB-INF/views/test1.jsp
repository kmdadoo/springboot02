<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex08 Lombok</title>
	</head>
	<body>
		<%
		    out.println("Lombok : Hello World");
		%>
		<!-- 커멘드 객체 변수의 속성값을 이용 -->
		<br>
		당신의 아이디는 ${member.id} 입니다.<br>
		당신의 이름은 ${member.name} 입니다.
	</body>
</html>