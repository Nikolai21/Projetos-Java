package com.gerenciador.acao;

import java.io.IOException;

import com.gerenciador.model.BancoDeDados;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RemoveEmpresa implements Acao {
	
	public String executa (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String idString = request.getParameter("id");
		Integer id = Integer.valueOf(idString);
		BancoDeDados.removeEmpresa(Integer.valueOf(id));

		return "redirect:controller?acao=ListaEmpresa";

	}

}
