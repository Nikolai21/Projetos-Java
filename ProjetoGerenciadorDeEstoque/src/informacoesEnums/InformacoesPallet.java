package informacoesEnums;

public enum InformacoesPallet {
	
	LARGURACM (80),
	COMPRIMENTOCM (100),
	PESOBRUTOKG (10),
	CAPACIDADEMAXIMA(1000),
	CAIXASPORCAMADA (20),
	MAXIMOCAMADAS (5),
	CAPACIDADEMAXIMACAIXAS (100);
	
	private int informacoes;
	
	InformacoesPallet (int informacoes) {
		this.informacoes = informacoes;
	}
	
	public int getInformacoes () {
		return informacoes;
	}
	

}
