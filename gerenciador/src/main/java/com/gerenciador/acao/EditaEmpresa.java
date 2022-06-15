package com.gerenciador.acao;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gerenciador.model.BancoDeDados;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditaEmpresa implements Acao {

	public String executa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String idString = request.getParameter("id");
		Integer id = Integer.valueOf(idString);
		
		String novoNome = request.getParameter("nome");
		String novoCnpj = request.getParameter("cnpj");
		String novaDataAberturaRaw = request.getParameter("data");
		Date novaDataAbertura = null;

		try {
			SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
			novaDataAbertura = dateParser.parse(novaDataAberturaRaw);
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		
		BancoDeDados.editaEmpresa(id, novoNome, novoCnpj, novaDataAbertura);
		
		return "redirect:controller?acao=ListaEmpresa";
		
		

	}
		
}


