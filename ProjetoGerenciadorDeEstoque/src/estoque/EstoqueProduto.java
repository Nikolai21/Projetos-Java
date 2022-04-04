package estoque;

import static estoque.AlteracaoEstoqueUtil.*;

import excecoes.EstoqueInsuficienteException;
import excecoes.QuantidadeMinimaException;
import excecoes.QuantidadeNaoMultiplaDe1000Exception;
import excecoes.checkError;
import informacoesEnums.InformacoesCaixa;
import informacoesEnums.InformacoesPallet;

public class EstoqueProduto extends EstoqueGeral {
	
	private double valorDeEstoque;
	private int quantidadePallets;
	private int quantidadeCaixas;
	private int capacidadeMaximaPallet = InformacoesPallet.CAPACIDADEMAXIMA.getInformacoes();
	private int capacidadeMaximaCaixa = InformacoesCaixa.CAPACIDADEPRODUTO.getDimensoesECapacidade();
	
	public EstoqueProduto (Produto produto, int quantidade) {
		//cria o estoque espec�fico do produto dentro do sistema
		checkError.checkQuantidadeGeral(quantidade);
		
		produto.setQuantidade(quantidade);
		this.valorDeEstoque = produto.getPreco() * produto.getQuantidadeTotal();
		super.setValorEstoque(valorDeEstoque);
		this.quantidadePallets = quantidade / capacidadeMaximaPallet; 
		super.setQuantidadePallets(quantidadePallets); 
		this.quantidadeCaixas = quantidade / capacidadeMaximaCaixa;
		super.setQuantidadeTotalCaixas(quantidadeCaixas); 
		
	}
	
	private void alteraEstoque (EstoqueProduto estoqueProduto, Produto produto, int quantidade, String modo) {
		
		if (modo == "Entrada") {
			AlteracaoEstoque(estoqueProduto, produto, quantidade);
			geraEntrada(produto, quantidade);
			EstoqueGeral.contadorEntradaNoEstoque(1);
		} else if (modo == "Saida") {
			AlteracaoEstoque(estoqueProduto, produto, defineQuantidadeParaSaida(quantidade));
			geraSaida(produto, quantidade);
			EstoqueGeral.contadorSaidaDoEstoque(1);			
		} 
		
	}
	
	private int defineQuantidadeParaSaida(int quantidade) {
		return quantidade *= -1;
	}
	
	public void saidaProduto (EstoqueProduto estoqueProduto, Produto produto, int quantidade) {
		//realiza a saida de produtos no estoque
		System.out.println("Gerando carga...");			
		alteraEstoque (estoqueProduto, produto, quantidade, "Saida");

	}

	public void entradaProduto (EstoqueProduto estoqueProduto, Produto produto, int quantidade) {
		//implementa da entrada de produtos no estoque
		checkError.checkQuantidadeEntrada(quantidade);
		System.out.println("Gerando entrada...");
		alteraEstoque (estoqueProduto, produto, quantidade, "Entrada");
		System.out.println("Produto adicionado com sucesso");
	}
	
	protected void setQuantidadeCaixas (int quantidadeCaixas) {
		this.quantidadeCaixas += quantidadeCaixas;
		setQuantidadeTotalCaixas(quantidadeCaixas);
	}
	
	public int getQuantidadeCaixas () {
		return this.quantidadeCaixas;
	}
	
	@Override
	protected void setQuantidadePallets (int quantidadePallets) {
		this.quantidadePallets += quantidadePallets;
		super.setQuantidadePallets(quantidadePallets);
	}
	
	@Override
	public int getQuantidadePallets () {
		return this.quantidadePallets;
	}
	
	@Override
	protected void setValorEstoque (double valor) {
		this.valorDeEstoque += valor;
		super.setValorEstoque(valor);
	}
	
	@Override
	public double getValorEstoque () {
		return this.valorDeEstoque;
	}
}
