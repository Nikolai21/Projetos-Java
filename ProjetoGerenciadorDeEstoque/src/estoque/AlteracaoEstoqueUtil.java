package estoque;

import informacoesEnums.InformacoesCaixa;
import informacoesEnums.InformacoesPallet;
import excecoes.checkError;


class AlteracaoEstoqueUtil {
		
	public AlteracaoEstoqueUtil (EstoqueProduto estoqueProduto, Produto produto, int quantidade) {
		
		if (quantidade < 0) {
			quantidade *= -1;
			checkError.main(produto, quantidade);
			quantidade *= -1;
		} else {
			checkError.main(produto, quantidade);
		}
		
		produto.setQuantidade(quantidade);
		estoqueProduto.setValorEstoque(produto.getPreco() * quantidade);
		int quantidadeCaixas = quantidade / InformacoesCaixa.CAPACIDADEPRODUTO.getDimensoesECapacidade();
		int quantidadePallets = quantidadeCaixas / InformacoesPallet.CAPACIDADEMAXIMACAIXAS.getInformacoes();
		estoqueProduto.setQuantidadePallets(quantidadePallets);
		estoqueProduto.setQuantidadeCaixas(quantidadeCaixas);
		
	}
	
	public static void geraCarga(Produto produto, int quantidade) {
		
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

}
