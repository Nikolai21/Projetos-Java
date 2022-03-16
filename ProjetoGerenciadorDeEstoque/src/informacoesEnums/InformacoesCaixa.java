package informacoesEnums;

public enum InformacoesCaixa {
	
	ALTURACM (20),
	LARGURACM (20),
	COMPRIMENTOCM (20),
	CAPACIDADEPRODUTO (10),
	PESOBRUTOKG (0.01);
	
	private int dimensoesECapacidade;
	private double pesoBruto;
	
	InformacoesCaixa (int dimensoesECapacidade) {
		this.dimensoesECapacidade = dimensoesECapacidade;
	}
	
	InformacoesCaixa (double pesoBruto) {
		this.pesoBruto = pesoBruto;
	}
	
	public int getDimensoesECapacidade () {
		return this.dimensoesECapacidade;
	}
	
	public double getPesoBruto () {
		return this.pesoBruto;
	}
}
