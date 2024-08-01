<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ex16 MyBatis SimpleBBS</title>
	</head>
	<body>
		<table width="500" cellpadding="0" cellspacing="0" border="1">
			<tr>
				<td>번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>삭제</td>
			<tr>
			<!-- key, value는 순서가 바뀌어도 상관 없음 -->
			<c:forEach items="${list}" var="dto">
			<tr>
				<td>${dto.id}</td>
				<td>${dto.writer}</td>
				<td><a href="view?id=${dto.id}">${dto.title}</a></td>
				<td><a href="delete?id=${dto.id}">X</a></td>
			<tr>
			</c:forEach>
		</table>
		
		<p><a href="writeForm">글작성</a></p>
	</body>
</html>