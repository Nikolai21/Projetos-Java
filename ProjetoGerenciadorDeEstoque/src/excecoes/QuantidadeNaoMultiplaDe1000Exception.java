package excecoes;

public class QuantidadeNaoMultiplaDe1000Exception extends RuntimeException {
	
	private static String msg = "Erro na opera��o: quantidade deve ser multipla de 1000";
	
	public QuantidadeNaoMultiplaDe1000Exception () {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
