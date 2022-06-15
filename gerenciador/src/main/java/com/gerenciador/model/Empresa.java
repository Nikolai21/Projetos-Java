package com.gerenciador.model;

import java.util.Date;

public class Empresa {
	
	private Integer id = 0;
	private String nomeEmpresa;
	private String cnpjEmpresa;
	private Date dataAbertura;
	
	public Empresa(String nomeEmpresa, String cnpjEmpresa, Date dataAbertura) {
		this.nomeEmpresa = nomeEmpresa;
		this.cnpjEmpresa = cnpjEmpresa;
		this.dataAbertura = dataAbertura;
	}
	
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}
	
	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}
	
	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}
	
	public Date getDataAbertura() {
		return dataAbertura;
	}
	
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "[Nome: " + nomeEmpresa + ", CPNJ: " + cnpjEmpresa + "]";
	}
	
}
