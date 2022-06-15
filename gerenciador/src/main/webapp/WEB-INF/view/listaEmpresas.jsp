<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List,com.gerenciador.model.Empresa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url value="/controller?acao=NovaEmpresaForm" var="linkServletNovaEmpresa"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Empresas cadastradas</title>
	</head>
	
	<body>
		
		Usuário logado: ${usuarioLogado.login} 
		
		<br/>
		
		<c:import url="logout-parcial.jsp" />
		
		<br/>
		
		<c:if test="${not empty nome}">
			Empresa ${nome} cadastrada com sucesso!
		</c:if>
		
		<br/>
		<br/>	
		Lista de empresas: 
		<br/>
		
		<ul>
			<c:forEach items="${lista}" var="empresa">
				<li> 
					 Nome: ${empresa.nomeEmpresa}, 
				     CNPJ: ${empresa.cnpjEmpresa} 
				     - Data da abertura: <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/> 
				     <a href="/gerenciador/controller?acao=MostraEmpresa&id=${empresa.id}">Editar</a>
				     <a href="/gerenciador/controller?acao=RemoveEmpresa&id=${empresa.id}">Remover</a>
			   </li>	

			
			</c:forEach>
		</ul>
		
		<br/>
		
	 	<form action="${linkServletNovaEmpresa}" method="post">
			<input type="submit" value="Cadastrar empresa"/>
		</form>
	</body>
</html>