<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- jstl을 사용하기 위한 태그 라이브러리를 추가 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex06 Model</title>
	</head>
	<body>
		<%
		    out.println("Model(Sub) : Hello World");
		%>
		<br>
		 
		${ObjectTest}
		 
		<br>
		 
		${lists}
		 
		<br>
		<br>
		 
		<c:forEach var="mylist" items="${lists}">
		    ${mylist} <br>
		</c:forEach>
		
		<br>
		<br>
		당신의 이름은 ${name} 입니다.
	</body>
</html>