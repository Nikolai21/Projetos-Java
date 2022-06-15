package com.gerenciador.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public class BancoDeDados {
	
	private static Integer idControlEmpresa = 1;
	private static List<Empresa> listaDeEmpresa = new ArrayList<Empresa>();
	private static List<Usuario> listaDeUsuario = new ArrayList<Usuario>();

	
	static {
		Date data = new Date();
		adicionaEmpresa(new Empresa("Googol Ltd", "10000000000000000000", data));
		adicionaEmpresa(new Empresa("Pi Inc.", "314159265359", data));
		adicionaEmpresa(new Empresa("Phi SA", "161803398875", data));
		
		adicionaUsuario(new Usuario("adm", "adm"));
		adicionaUsuario(new Usuario("User1", "12345"));
	}
		
	public static void adicionaEmpresa (Empresa empresa) {
		empresa.setId(idControlEmpresa);
		listaDeEmpresa.add(empresa);
		idControlEmpresa ++;		
	}
	
	public static void adicionaUsuario (Usuario usuario) {
		listaDeUsuario.add(usuario);
	}
	
	public static List<Empresa> getListaDeEmpresas() {
		return BancoDeDados.listaDeEmpresa;
	}
	
	public static Empresa buscaEmpresa(Integer id) {
		return listaDeEmpresa.stream()
				.filter(c -> c.getId() == id)
				.findFirst()
				.get();
	}
	
	public static void removeEmpresa(Integer id) {
		listaDeEmpresa.remove(buscaEmpresa(id));
	}
	
	public static void editaEmpresa(Integer id, String novoNome, String novoCnpj, Date novaData) {
		Empresa empresa = buscaEmpresa(id);
		empresa.setNomeEmpresa(novoNome);
		empresa.setCnpjEmpresa(novoCnpj);
		empresa.setDataAbertura(novaData);
	}
	
	public static Usuario buscaUsuario (String login, String senha) {
		int userId = login.hashCode() + senha.hashCode();
		
		try {
			return listaDeUsuario.stream()
					         .filter(c -> c.getIdUser() == userId)
					         .findFirst()
					         .get();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}
}
