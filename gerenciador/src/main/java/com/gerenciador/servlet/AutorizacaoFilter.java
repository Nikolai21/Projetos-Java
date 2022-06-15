package com.gerenciador.servlet;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns="/controller")
public class AutorizacaoFilter implements Filter {
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest requestServlet, ServletResponse responseServlet, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) requestServlet;
		HttpServletResponse response = (HttpServletResponse) responseServlet;
		
		String acao = request.getParameter("acao");
				
		boolean usuarioNaoLogado = (request.getSession().getAttribute("usuarioLogado") == null);
		boolean paginaProtegida = !(acao.equals("Login") || acao.equals("LoginForm"));
		
		if (paginaProtegida && usuarioNaoLogado) {
			response.sendRedirect("controller?acao=LoginForm");
			return;
		} 
		
		//Ao utilizar Filter, para que a requisição seja passada adiante, é necessário chamar o método doFilter
		//passando a request e a response como argumentos. Caso contrário, a requisição ficará parada no filtro
		chain.doFilter(request, response);
		
	}

}
