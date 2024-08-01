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
	
		<div layout:fragment="content">
			<h1>Error Page</h1>
			error code : <span th:text="${code}"></span><br>
			error msg : <span th:text="${msg}"></span><br>
			timestamp : <span th:text="${timestamp}"></span><br>
		</div>
	
	</body>
</html>