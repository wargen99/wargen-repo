<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Log In page</title>
</head>
<body>
	<div id="form-login">
		<h2>login</h2>
		<form:form method="post" action="/redisSession/LoginCheck">
			<input type="text" name="username" placeholder="UserName" />
			<input type="password" name="password" placeholder="Password" />
			<input type="submit" value="login" />
		</form:form>
	</div>
</body>
</html>