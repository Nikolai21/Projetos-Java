<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/controller?acao=Login" var="linkServletLogin"/>

<!DOCTYPE html>
<html lang="pt_br">
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
	</head>
	
	<body>
		<form action="${linkServletLogin}" method="post">
			Login: <input type="text" name="login"/> 
			Senha: <input type="password" name="senha"/>
			<input type="submit" value="Entrar"/>
			
		</form>
	</body>
</html>