<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome</title>
	</head>
	<body>
		welcome : Member
		<hr>

		<c:if test="${not empty pageContext.request.userPrincipal }">
		<p> is Log-In</p>
		</c:if>
		
		<c:if test="${empty pageContext.request.userPrincipal }"><!--나오지 않음-->
		<p> is Log-Out</p>
		</c:if> 
		
<%-- 		<sec:authorize access="isAuthenticated()">   --%>
<!-- 		<p> Log-In</p> -->
<%-- 		</sec:authorize> --%>
		
<%-- 		<sec:authorize access="!isAuthenticated()">   --%>
<!-- 		<p> Log-Out</p> -->
<%-- 		</sec:authorize> --%>
		
		USER ID : ${pageContext.request.userPrincipal.name}<br/>
<%-- 		USER ID : <sec:authentication property="name"/><br/>   --%>
		<!-- 세션을 쓰는게 좋음. -->
<%-- 		<c:url value="/logout" var="logoutUrl" />   변경 --%>
		<a href="${logoutUrl}">Log Out</a> <br />
	</body>
</html>