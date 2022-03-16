package estoque;

public class Produto {
	
	private String nomeProduto;
	private String codigoProduto;
	private double pesoLiquidoUnitario;
	private double preco;
	private int quantidadeTotal;
	
	public Produto (String nomeProduto, String codigoProduto, double pesoLiquidoUnitario, double preco) {
		this.nomeProduto = nomeProduto;
		this.codigoProduto = codigoProduto;
		this.pesoLiquidoUnitario = pesoLiquidoUnitario; //implementar tratamento de erro -> peso líquido negativo 
		this.preco = preco;	//implementar tratamento de erro -> preço negativo	
	}
	
	public void setNomeProduto (String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	
	public String getNomeProduto () {
		return this.nomeProduto;
	}
	
	public void setCodigoProduto (String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}
	
	public String getCodigoProduto () {
		return this.codigoProduto;
	}
	
	public void setPesoLiquidoUnitario (double pesoLiquidoUnitario) {
		this.pesoLiquidoUnitario = pesoLiquidoUnitario;		
	}
	
	public double getPesoLiquidoUnitario () {
		return this.pesoLiquidoUnitario;
	}
	
	public void setPreco (double preco) {
		this.preco = preco;
	}
	
	public double getPreco () {
		return this.preco;
	}
	
	public void setQuantidade (int quantidadeTotal) {
		this.quantidadeTotal += quantidadeTotal;
	}
	
	public int getQuantidadeTotal () {
		return this.quantidadeTotal;
	}
}
