import estoque.EstoqueProduto;
import estoque.Produto;

public class Teste {
	
	public static void main(String[] args) {
		
		Produto remedio = new Produto ("RemedioNovo", "11125", 0.01, 100);
		EstoqueProduto estoqueRemedio = new EstoqueProduto(remedio, 10000);
		System.out.println(estoqueRemedio.getQuantidadePallets());
		
		System.out.println(estoqueRemedio.getValorEstoque());
		estoqueRemedio.saidaProduto(estoqueRemedio, remedio, 1000);
		System.out.println(estoqueRemedio.getValorEstoque());
		estoqueRemedio.entradaProduto(estoqueRemedio, remedio, 1000);
		System.out.println(estoqueRemedio.getValorEstoque());
		System.out.println(estoqueRemedio.getQuantidadePallets());
		
	}

}
