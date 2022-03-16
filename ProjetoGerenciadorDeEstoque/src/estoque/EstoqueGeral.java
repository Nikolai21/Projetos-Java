package estoque;

public abstract class EstoqueGeral {
	
	private static double valorTotalEstoque;	
	private static int quantidadeTotalPallets;
	private static int quantidadeTotalCaixas;
	private static int quantidadeSaidas;
	private static int quantidadeEntradas;
	
	protected void setValorEstoque (double valor) {
		valorTotalEstoque += valor;
	}
	
	public double getValorEstoque () {
		return valorTotalEstoque;
	}
	
	protected void setQuantidadePallets (int quantidade) {
		quantidadeTotalPallets += quantidade;
	}
	
	public int getQuantidadePallets () {
		return quantidadeTotalPallets;
	}
	
	protected void setQuantidadeTotalCaixas (int quantidade) {
		quantidadeTotalCaixas += quantidade;
	}
	
	public int getQuantidadeTotalCaixas () {
		return quantidadeTotalCaixas;
	}
	
	protected static void setQuantidadeSaidas(int saida) {
		quantidadeSaidas += saida;
	}

	public static int getQuantidadeSaidas() {
		return quantidadeSaidas;
	}
	
	protected static void setQuantidadeEntradas(int entrada) {
		quantidadeEntradas += entrada;
	}
	
	public static int getQuantidadeEntradas() {
		return quantidadeEntradas;
	}
	
}
