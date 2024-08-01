<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex30 Error Mapping</title>
	</head>
	<body>
	
		<%
			out.println(2 / 0);
		%>
	</body>
</html>