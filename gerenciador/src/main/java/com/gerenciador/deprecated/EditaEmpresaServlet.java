package com.gerenciador.deprecated;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gerenciador.model.BancoDeDados;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditaEmpresaServlet
 */
@WebServlet(urlPatterns="/editaEmpresa")
public class EditaEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		response.sendRedirect("listaEmpresa");

	}

}
