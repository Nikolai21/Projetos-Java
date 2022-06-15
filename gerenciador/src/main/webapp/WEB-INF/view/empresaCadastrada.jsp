<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/controller?acao=NovaEmpresaForm" var="linkServletNovaEmpresa"/>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
	</head>
	
	<body>
		<c:import url="logout-parcial.jsp" />
		
		<br/>
		
		<c:if test="${not empty nome}">
			Empresa ${nome} cadastrada com sucesso!
		</c:if>
		
		<c:if test="${empty nome}">
			Nenhuma empresa foi cadastrada.
		</c:if>
		
		<form action="${linkServletNovaEmpresa}" method="post">
			<input type="submit" value="Voltar"/>
		</form>
	</body>
</html>