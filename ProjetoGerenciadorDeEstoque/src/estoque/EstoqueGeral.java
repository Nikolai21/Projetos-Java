package estoque;

public abstract class EstoqueGeral {
	
	private static double valorTotalEstoque;	
	private static int quantidadeTotalPallets;
	private static int quantidadeTotalCaixas;
	private static int contadorSaidas;
	private static int contadorEntradas;
	
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
	
	protected static void contadorSaidaDoEstoque(int saida) {
		contadorSaidas += saida;
	}

	public static int getQuantidadeSaidas() {
		return contadorSaidas;
	}
	
	protected static void contadorEntradaNoEstoque(int entrada) {
		contadorEntradas += entrada;
	}
	
	public static int getQuantidadeEntradas() {
		return contadorEntradas;
	}
	
}
