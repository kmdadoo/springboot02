<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!-- 추가 -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page import="com.study.springboot.oauth2.SessionUser" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome</title>
	</head>
	<body>
		welcome : Member
		<hr>

		<sec:authorize access="isAuthenticated()">  
		<p> Log-In</p>
		</sec:authorize>
			ID : <sec:authentication property="name"/><br/>
			소유권한 : <sec:authentication property="authorities"/><br/>
		
		<%
			request.setCharacterEncoding("UTF-8");
		
			SessionUser obj = (SessionUser) session.getAttribute("user");
			String sName = (String)obj.getName();
			String sEmail = (String)obj.getEmail();
			String sPicture = (String)obj.getPicture();
		%>
		
		<%= sName %>
		<%= sEmail %>
		<img src=<%= sPicture %>><br>
		
		<a href="/logout">Log Out</a> <br />
		
		<script>
		// '#_=_'은  보안 세션 악용을 보호하기 위해 Facebook(META)에 의해 추가되었다.
		// Facebook에 따르면 이를 처리하는 것은 개발자의 책임이다.
		console.log("aaa" + window.location.hash);
		if (window.location.hash == '#_=_'){
			console.log("bbb");
			
			history.replaceState
				? history.replaceState(null, null, window.location.href.split('#')[0])
				: window.location.hash = '';
		}
		</script>
	</body>
</html>