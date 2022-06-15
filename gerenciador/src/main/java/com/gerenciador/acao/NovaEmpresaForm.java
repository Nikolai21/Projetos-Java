package com.gerenciador.acao;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NovaEmpresaForm implements Acao {

	public String executa (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		return "forward:novaEmpresa.jsp";
		
	}
	
	

}
