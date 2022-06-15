package com.gerenciador.deprecated;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gerenciador.model.BancoDeDados;
import com.gerenciador.model.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NovaEmpresaServ
 */
@WebServlet(urlPatterns="/novaEmpresa")
public class NovaEmpresaServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * O método service aceita tanto requisições GET como requisições POST,
	 * porém, em muitos casos, o servidor não pode aceitar requisições GET por razões de segurança.
	 * Para isso existe o método doPost. Esse método também recebe uma request e uma response,
	 * mas as requests só podem ser do tipo POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Pega o parâmetro "nome" definido no html e retorna uma string com o valor inserido
		 */
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

		
		/*
		 * Retorna um html informando que a empresa de nome definido pelo usuário e capturado por
		 * request.getParameter() foi cadastrada
		 * 
		 * writer.println("<html><body> Empresa " + nomeEmpresa + " cadastrada com sucesso! </body></html>");
		 */
		
		//request.setAttribute define um atributo que poderá ser chamado numa JSP
		request.setAttribute("nome", nomeEmpresa);
		/*A classe request dispatcher define um dispatcher para uma JSP. 
		* Devemos passar o path da JSP para que o servlet faça o redirecionamento corretamente
		* também, dependendo da necessidade, podemos passar um outro servlet ao dispatcher
		* 
		* Utilizar o dispatcher no servlet chama-se redirecionamento server-side
		*/
		request.getRequestDispatcher("/listaEmpresa");
		//dispatcher.forward realiza o redirecionamento do servlet para a JSP
		//dispatcher.forward(request, response);
		
		/* utilizando o método sendRedirect do objeto response, informamos ao cliente que
		 * um redirecionamento deverá ser realizado. Como parâmetro, é passado o urlPattern sem a barra
		 * 
		 * Utilizar o sendRedirect chama-se redirecionamento client-side
		 */
		response.sendRedirect("listaEmpresa");

		//O sysout apenas retorna no console do servidor
		System.out.println("Cadastro realizado.");
		
	}

}
