package com.gerenciador.servlet;

import java.io.IOException;

import com.gerenciador.acao.Acao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns="/controller")
public class ControladorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 5L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		String paramAcao = "com.gerenciador.acao." + acao;
		String destino = null;
		
		try {
			Acao acaoInterface = (Acao) Class.forName(paramAcao).newInstance();
			destino = acaoInterface.executa(request, response);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new ServletException(e);
		}	
		
		
		String[] acaoEDestino = destino.split(":");
		
		if (acaoEDestino[0].equals("forward")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/" + acaoEDestino[1]);
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(acaoEDestino[1]);
		}
		
	}
		
//		if (paramAcao.equals("listaEmpresa")) {
//			destino = ListaEmpresa.executaListar(request, response);
//		}
//		
//		if (paramAcao.equals("removeEmpresa")) {
//			destino = RemoveEmpresa.executaRemover(request, response);
//		}
//		
//		if (paramAcao.equals("mostraEmpresa")) {
//			destino = MostraEmpresa.executaMostrar(request, response);
//		}
//		
//		if (paramAcao.equals("editaEmpresa")) {
//			destino = EditaEmpresa.executaEditar(request, response);
//		}
//		
//		if (paramAcao.equals("cadastraEmpresa")) {
//			destino = CadastraEmpresa.executaCadastrar(request, response);
//		}
//		
//		if (paramAcao.equals("novaEmpresaForm")) {
//			destino = NovaEmpresaForm.executaRedirect(request, response);
//		}


}
