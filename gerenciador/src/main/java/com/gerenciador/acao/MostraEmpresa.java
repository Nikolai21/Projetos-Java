package com.gerenciador.acao;

import java.io.IOException;

import com.gerenciador.model.BancoDeDados;
import com.gerenciador.model.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MostraEmpresa implements Acao {
	
	public String executa (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("id");
		Integer id = Integer.valueOf(idString);
		
		Empresa empresa = BancoDeDados.buscaEmpresa(id);
		
		request.setAttribute("empresa", empresa);

		return "forward:editaEmpresa.jsp";

		
	}

}
