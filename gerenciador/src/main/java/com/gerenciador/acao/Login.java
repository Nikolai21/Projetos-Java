package com.gerenciador.acao;

import java.io.IOException;

import com.gerenciador.model.BancoDeDados;
import com.gerenciador.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login implements Acao {

	@Override
	public String executa(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		Usuario user = BancoDeDados.buscaUsuario(login, senha);
		
		if (user != null) {
			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuarioLogado", user);
			return "redirect:controller?acao=ListaEmpresa";
		} else {
			return "forward:login.jsp";
		}
	}

}
