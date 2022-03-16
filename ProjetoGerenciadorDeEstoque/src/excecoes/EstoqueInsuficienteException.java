package excecoes;

public class EstoqueInsuficienteException extends RuntimeException {
	
	private static String msg = "Erro na operação: quantidade insuficiente em estoque.";
	
	public EstoqueInsuficienteException () {
		super(msg);
	}
	
	@Override
	public String getMessage() {
		return msg;
	}
	
}
