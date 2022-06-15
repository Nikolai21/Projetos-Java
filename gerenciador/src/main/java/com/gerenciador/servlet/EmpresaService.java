package com.gerenciador.servlet;

import java.io.IOException;
import java.util.List;

import com.gerenciador.model.BancoDeDados;
import com.gerenciador.model.Empresa;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/empresa")
public class EmpresaService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Empresa> listaEmpresas = BancoDeDados.getListaDeEmpresas();
		
		String header = request.getHeader("Accept");
		
		if (header.contains("json")) {
			/*Gson é uma lib para trabalhar com JSON
			Cria uma nova instância do objeto JSON */
			Gson json = new Gson();
			//o método .toJson faz o parse de um objeto genérico para JSON e devolve em String
			String listaJSON = json.toJson(listaEmpresas);
			
			//Define o tipo de conteúdo que será retornado para o cliente
			response.setContentType("application/json");
			response.getWriter().println(listaJSON);
			return;
		}
		
		if (header.contains("xml")) {
			XStream xstream = new XStream();
			xstream.alias("empresa", Empresa.class);
			String listaXML = xstream.toXML(listaEmpresas);
			
			response.setContentType("application/xml");
			response.getWriter().println(listaXML);
			return;
		}

		response.getWriter().println("{'message':'no content'}");
		
		
		
	}

}
