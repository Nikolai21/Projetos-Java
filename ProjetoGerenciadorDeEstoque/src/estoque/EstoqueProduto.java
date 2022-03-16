package estoque;

import excecoes.EstoqueInsuficienteException;
import excecoes.QuantidadeMinimaException;
import excecoes.QuantidadeNaoMultiplaDe1000Exception;
import excecoes.checkError;
import informacoesEnums.InformacoesCaixa;
import informacoesEnums.InformacoesPallet;

/**
 * A classe EstoqueProduto � uma subclasse de EstoqueGeral. � utilizada para criar o estoque de um produto espec�fico
 * dentro do estoque geral, armazenando dados espec�ficos daquele produto, bem como para realizar as opera��es de entrada
 * e sa�da de uma mercadoria.
 * A classe EstoqueProduto utiliza um utilit�rio chamado Altera��oEstoqueUtil para evitar a repeti��o de c�digo nos
 * m�todos saidaEstoque e entradaEstoque.
 * 
 * @author Nikolai Fiathoski
 * @version 1.0
 * @since JDK 17.0.1
 *
 */


public class EstoqueProduto extends EstoqueGeral {
	
	@SuppressWarnings("unused")
	private AlteracaoEstoqueUtil utilitario;
	private double valorDeEstoque;
	private int quantidadePallets;
	private int quantidadeCaixas;
	private int capacidadeMaximaPallet = InformacoesPallet.CAPACIDADEMAXIMA.getInformacoes();
	private int capacidadeMaximaCaixa = InformacoesCaixa.CAPACIDADEPRODUTO.getDimensoesECapacidade();
	
	/**
	 * Construtor utilizado para realizar a cria��o do estoque de um produto espec�fico. 
	 * 
	 * @param produto
	 * @param quantidade
	 */
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
	
	/**
	 * M�todo privado utilizado para realizar a altera��o do estoque nas opera��es de entrada e sa�da de mercadoria.
	 * 
	 * @param estoqueProduto
	 * @param produto
	 * @param quantidade
	 * @param modo
	 */
	
	private void alteracaoEstoque (EstoqueProduto estoqueProduto, Produto produto, int quantidade, String modo) {
		
		if (modo == "Entrada") {
			this.utilitario = new AlteracaoEstoqueUtil(estoqueProduto, produto, quantidade);
		} else if (modo == "Saida") {
			quantidade *= - 1;
			this.utilitario = new AlteracaoEstoqueUtil(estoqueProduto, produto, quantidade);
		} 
		
	}
	
	/**
	 * M�todo utilizado para realizar a opera��o de sa�da de mercadoria. O m�todo chama o m�todo privado alteracaoEstoque e,
	 * caso a opera��o seja bem sucedida, a carga � gerada. Caso haja algum problema, alguma das seguintes exce��es poder� 
	 * ser lan�ada: QuantidadeNaoMultiplaDe1000Exception, QuantidadeMinimaException, EstoqueInsuficienteException.
	 * 
	 * @param estoqueProduto
	 * @param produto
	 * @param quantidade
	 */
	
	public void saidaProduto (EstoqueProduto estoqueProduto, Produto produto, int quantidade) {
		//realiza a saida de produtos no estoque
		try {
			alteracaoEstoque (estoqueProduto, produto, quantidade, "Saida");
			
			System.out.println("Gerando carga...");
			
			EstoqueGeral.setQuantidadeSaidas(1);
			
			double valorTotal = quantidade * produto.getPreco();
			double pesoLiquidoTotalCarga = quantidade * produto.getPesoLiquidoUnitario();
			int quantidadeCaixas = quantidade / InformacoesCaixa.CAPACIDADEPRODUTO.getDimensoesECapacidade();
			int quantidadeTotalPallets = quantidadeCaixas / InformacoesPallet.CAPACIDADEMAXIMACAIXAS.getInformacoes();
			
			double pesoBrutoTotalCarga;
			double pesoBrutoCaixas = InformacoesCaixa.PESOBRUTOKG.getPesoBruto() * quantidadeCaixas;
			double pesoBrutoTotalCaixas = pesoBrutoCaixas * produto.getPesoLiquidoUnitario() * quantidade;
			double pesoBrutoPallets = InformacoesPallet.PESOBRUTOKG.getInformacoes() * quantidadeTotalPallets;
			pesoBrutoTotalCarga = pesoBrutoPallets + pesoBrutoTotalCaixas;
			
			String dimensoes; 
			int alturaPallet = InformacoesCaixa.ALTURACM.getDimensoesECapacidade() * InformacoesPallet.MAXIMOCAMADAS.getInformacoes(); 
			int larguraPallet = InformacoesPallet.LARGURACM.getInformacoes();
			int comprimentoPallet = InformacoesPallet.COMPRIMENTOCM.getInformacoes();
			dimensoes = alturaPallet + " cm x " + larguraPallet + " cm x " + comprimentoPallet + " cm";
			
			System.out.println("Carga gerada com sucesso:\n" +
					"Produto: " + produto.getNomeProduto() + "\n" +
					"C�digo: " + produto.getCodigoProduto() + "\n" +
					"Valor unit�rio: R$ " + produto.getPreco() + "\n" +
					"Quantidade total: " + quantidade + " unidades\n" +
					"Valor total: R$ " + valorTotal + "\n" +
					"Peso l�quido: " + pesoLiquidoTotalCarga + " Kg\n" +
					"Peso bruto: " + pesoBrutoTotalCarga + " Kg\n" +
					"Quantidade caixas: " + quantidadeCaixas + "\n" +
					"Quantidade pallets: " + quantidadeTotalPallets + "\n" + 
					"Dimens�es dos pallets: " + dimensoes + "\n");
		}
		catch (QuantidadeNaoMultiplaDe1000Exception | QuantidadeMinimaException | EstoqueInsuficienteException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * M�todo utilizado para realizar a opera��o de entrada de mercadoria. O m�todo chama o m�todo privado alteracaoEstoque e,
	 * caso a opera��o seja bem sucedida, a mercadoria � adicionada ao estoque. Caso haja algum problema, alguma das seguintes 
	 * exce��es poder� ser lan�ada: QuantidadeNaoMultiplaDe1000Exception, QuantidadeMinimaException.
	 * 
	 * @param estoqueProduto
	 * @param produto
	 * @param quantidade
	 */
	
	public void entradaProduto (EstoqueProduto estoqueProduto, Produto produto, int quantidade) {
		//implementa da entrada de produtos no estoque
		try {
			checkError.checkQuantidadeEntrada(quantidade);
			
			alteracaoEstoque (estoqueProduto, produto, quantidade, "Entrada");
			
			EstoqueGeral.setQuantidadeEntradas(1);
	
			System.out.println("Adicionando produto no estoque...\n" +
					"Produto: " + produto.getNomeProduto() + "\n" +
					"Quantidade: " + quantidade + "\n" +
					"Valor de entrada: R$ " + (produto.getPreco()*quantidade) + "\n" +
					"Produto adicionado com sucesso.");
		}
		catch (QuantidadeNaoMultiplaDe1000Exception | QuantidadeMinimaException ex) {
			System.out.println(ex.getMessage());
		}
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
