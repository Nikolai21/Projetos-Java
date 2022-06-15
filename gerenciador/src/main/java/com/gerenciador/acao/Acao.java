package com.gerenciador.acao;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Acao {
	
	String executa(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

}
