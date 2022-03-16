package excecoes;

public class EstoqueInsuficienteException extends RuntimeException {
	
	private static String msg = "Erro na opera��o: quantidade insuficiente em estoque.";
	
	public EstoqueInsuficienteException () {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
	
}
