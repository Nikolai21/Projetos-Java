package com.gerenciador.acao;

import java.io.IOException;
import java.util.List;

import com.gerenciador.model.BancoDeDados;
import com.gerenciador.model.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListaEmpresa implements Acao {
	
	public String executa (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> lista = BancoDeDados.getListaDeEmpresas();
		
		request.setAttribute("lista", lista);
		
		return "forward:listaEmpresas.jsp";
		
	}

}
