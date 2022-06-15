<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/controller?acao=CadastraEmpresa" var="linkServletNovaEmpresa"/>

<!DOCTYPE html>
<html lang="pt_br">
	<head>
		<meta charset="UTF-8">
		<title>Cadastro nova empresa</title>
	</head>
	
	<body>
		<c:import url="logout-parcial.jsp" />
		
		<br/>
		
		<form action="${linkServletNovaEmpresa}" method="post">
			Nome: <input type="text" name="nome"/> 
			CNPJ: <input type="text" name="cnpj"/>
			Data da abertura: <input type="text" name="data" placeholder="dd/MM/yyyy"/>
			<input type="submit" value="Cadastrar"/>
		</form>
		
		<br/>
		
		<form action="/gerenciador/controller?acao=ListaEmpresa" method="post">
			<input type="submit" value="Ver empresas cadastradas"/>
		</form>
	</body>
</html>