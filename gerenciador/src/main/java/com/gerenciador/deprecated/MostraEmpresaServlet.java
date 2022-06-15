package com.gerenciador.deprecated;

import java.io.IOException;

import com.gerenciador.model.BancoDeDados;
import com.gerenciador.model.Empresa;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MostraEmpresaServlet
 */
@WebServlet(urlPatterns="/mostraEmpresa")
public class MostraEmpresaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("id");
		Integer id = Integer.valueOf(idString);
		
		Empresa empresa = BancoDeDados.buscaEmpresa(id);
		
		request.setAttribute("empresa", empresa);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/editaEmpresa.jsp");
		dispatcher.forward(request, response);
	}

}
