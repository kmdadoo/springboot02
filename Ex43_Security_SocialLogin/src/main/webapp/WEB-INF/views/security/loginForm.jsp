<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>LoginForm</title>
		<!-- 자유롭게 디자인을 적용시키려고 이것을 사용함. -->
	</head>
	<body>
		<h1>loginForm.jsp</h1>
		
		<c:url value="j_spring_security_check" var="loginUrl"/> 
		<form action="<c:url value="j_spring_security_check" />" method="post"><!-- EL표기법 -->
			<c:if test="${param.error != null}">
			<p><!-- 에러 메세지 출력 -->
				Login Error! <br />
				${error_message}
			</p>
			</c:if>
			ID : <input type="text" name="j_username" value="${username}"> <br />
			PW : <input type="text" name="j_password"> <br />
			<input type="submit" value="LOGIN"> <br />
		</form>
		
		<a href="/oauth2/authorization/google">Google Login</a>	&nbsp;&nbsp;
		<a href="/oauth2/authorization/facebook">Facebook Login</a>	&nbsp;&nbsp;
		<a href="/oauth2/authorization/kakao">kakao Login</a>	&nbsp;&nbsp;
		<a href="/oauth2/authorization/naver">naver Login</a>	
	</body>
</html>