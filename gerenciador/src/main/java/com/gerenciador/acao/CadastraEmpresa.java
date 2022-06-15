package com.gerenciador.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gerenciador.model.BancoDeDados;
import com.gerenciador.model.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastraEmpresa implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String nomeEmpresa = request.getParameter("nome");
		String cnpjEmpresa = request.getParameter("cnpj");
		String dataAberturaRaw = request.getParameter("data");
		Date dataAbertura = null;

		try {
			SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
			dataAbertura = dateParser.parse(dataAberturaRaw);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
				
		BancoDeDados.adicionaEmpresa(new Empresa(nomeEmpresa, cnpjEmpresa, dataAbertura));

		request.setAttribute("nome", nomeEmpresa);
		
		return "redirect:controller?acao=ListaEmpresa";
		
	}
	
	

}
