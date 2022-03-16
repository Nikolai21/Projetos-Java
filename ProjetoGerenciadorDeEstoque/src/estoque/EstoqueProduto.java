package estoque;

import excecoes.EstoqueInsuficienteException;
import excecoes.QuantidadeMinimaException;
import excecoes.QuantidadeNaoMultiplaDe1000Exception;
import excecoes.checkError;
import informacoesEnums.InformacoesCaixa;
import informacoesEnums.InformacoesPallet;

/**
 * A classe EstoqueProduto é uma subclasse de EstoqueGeral. É utilizada para criar o estoque de um produto específico
 * dentro do estoque geral, armazenando dados específicos daquele produto, bem como para realizar as operações de entrada
 * e saída de uma mercadoria.
 * A classe EstoqueProduto utiliza um utilitário chamado AlteraçãoEstoqueUtil para evitar a repetição de código nos
 * métodos saidaEstoque e entradaEstoque.
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
	 * Construtor utilizado para realizar a criação do estoque de um produto específico. 
	 * 
	 * @param produto
	 * @param quantidade
	 */
	public EstoqueProduto (Produto produto, int quantidade) {
		//cria o estoque específico do produto dentro do sistema
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
	 * Método privado utilizado para realizar a alteração do estoque nas operações de entrada e saída de mercadoria.
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
	 * Método utilizado para realizar a operação de saída de mercadoria. O método chama o método privado alteracaoEstoque e,
	 * caso a operação seja bem sucedida, a carga é gerada. Caso haja algum problema, alguma das seguintes exceções poderá 
	 * ser lançada: QuantidadeNaoMultiplaDe1000Exception, QuantidadeMinimaException, EstoqueInsuficienteException.
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
					"Código: " + produto.getCodigoProduto() + "\n" +
					"Valor unitário: R$ " + produto.getPreco() + "\n" +
					"Quantidade total: " + quantidade + " unidades\n" +
					"Valor total: R$ " + valorTotal + "\n" +
					"Peso líquido: " + pesoLiquidoTotalCarga + " Kg\n" +
					"Peso bruto: " + pesoBrutoTotalCarga + " Kg\n" +
					"Quantidade caixas: " + quantidadeCaixas + "\n" +
					"Quantidade pallets: " + quantidadeTotalPallets + "\n" + 
					"Dimensões dos pallets: " + dimensoes + "\n");
		}
		catch (QuantidadeNaoMultiplaDe1000Exception | QuantidadeMinimaException | EstoqueInsuficienteException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Método utilizado para realizar a operação de entrada de mercadoria. O método chama o método privado alteracaoEstoque e,
	 * caso a operação seja bem sucedida, a mercadoria é adicionada ao estoque. Caso haja algum problema, alguma das seguintes 
	 * exceções poderá ser lançada: QuantidadeNaoMultiplaDe1000Exception, QuantidadeMinimaException.
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
