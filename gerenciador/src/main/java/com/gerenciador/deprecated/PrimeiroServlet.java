package com.gerenciador.deprecated;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//A anotação WebServlet declara a classe como um servlet ao Tomcat
//urlPatterns faz o mapeamento do servlet para uma URL
@WebServlet(urlPatterns="/primeiro")

//Para criar um servlet, é necessário extender a classe HttpServlet
//A classe HttpServlet é uma classe abstrata que provê os métodos 
//necessários para que a subclasse possa lidar com requisições http
public class PrimeiroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//O método service da classe HttpServlet recebe uma requisição Http e redireciona a requisição 
	//conforme a resposta definida dentro do método
	//Neste caso, foi definido que a resposta à requisição seria uma página html simples
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		PrintWriter resposta = response.getWriter();
		resposta.println("<html>");
		resposta.println("  <body>");
		resposta.println("<p>Olá mundo, este é o primeiro servlet do Nikolai</p>");
		resposta.println("  </body>");
		resposta.println("</html>");
	}
}
