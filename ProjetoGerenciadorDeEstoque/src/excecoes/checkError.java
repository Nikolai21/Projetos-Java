package excecoes;

import estoque.Produto;

public class checkError {
	
	public static boolean checkQuantidadeGeral(int quantidade) {
		if (quantidade < - 1000 || quantidade < 1000) {
			throw new QuantidadeMinimaException();
		} 
		else if (quantidade % 1000 != 0) {
			throw new QuantidadeNaoMultiplaDe1000Exception();
		}
		return true;
	}
	
	
	public static boolean checkEstoqueSaida(Produto produto, int quantidade) {
		if (quantidade > produto.getQuantidadeTotal()) {
			throw new EstoqueInsuficienteException();
		} 
		return true;
	}
	
	public static boolean checkQuantidadeEntrada(int quantidade) {
		if (quantidade < 1000) {
			throw new QuantidadeMinimaException();
		} 
		else if (quantidade % 1000 != 0) {
			throw new QuantidadeNaoMultiplaDe1000Exception();
		}
		return true;

	}
	
	
	public static void main(Produto produto, int quantidade) {
		checkQuantidadeGeral(quantidade);
		checkEstoqueSaida(produto, quantidade);
		
	}
}
	
