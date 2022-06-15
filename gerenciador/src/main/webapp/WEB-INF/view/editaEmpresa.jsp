<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:url value="/controller?acao=ListaEmpresa" var="linkServletListaEmpresa"/>
<c:url value="/controller?acao=EditaEmpresa" var="linkServletEditaEmpresa"/>


<!DOCTYPE html>
<html lang="pt_br">
	<head>
		<meta charset="UTF-8">
		<title>Editar empresa</title>
	</head>
	
	<body>
		
		<c:import url="logout-parcial.jsp" />
		
		<br/>
	
		<form action="${linkServletEditaEmpresa}" method="post">
			id: <input type="text" name="id" value="${empresa.id}" readonly/>
			Nome: <input type="text" name="nome" value="${empresa.nomeEmpresa}"/> 
			CNPJ: <input type="text" name="cnpj" value="${empresa.cnpjEmpresa}">
			Data da abertura: <input type="text" name="data" value="<fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/>"/>
			<input type="submit" value="Salvar"/>
		</form>
		
		<br/>
		
		<form action="${linkServletListaEmpresa}" method="post">
			<input type="submit" value="Voltar"/>
		</form>
	</body>
</html>