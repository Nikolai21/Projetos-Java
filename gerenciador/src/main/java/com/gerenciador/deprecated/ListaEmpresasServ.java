package com.gerenciador.deprecated;

import java.io.IOException;
import java.util.List;

import com.gerenciador.model.BancoDeDados;
import com.gerenciador.model.Empresa;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListaEmpresasServ
 */
@WebServlet(urlPatterns="/listaEmpresa")
public class ListaEmpresasServ extends HttpServlet {
	private static final long serialVersionUID = 2L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> lista = BancoDeDados.getListaDeEmpresas();
		
		request.setAttribute("lista", lista);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/listaEmpresas.jsp");
		dispatcher.forward(request, response);
       
	}
}
