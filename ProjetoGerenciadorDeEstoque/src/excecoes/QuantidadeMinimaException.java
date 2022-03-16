package excecoes;

public class QuantidadeMinimaException extends RuntimeException {
	
	private static String msg = "Erro na opera��o: quantidade minima deve ser de 1000 unidades";
	
	public QuantidadeMinimaException () {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
