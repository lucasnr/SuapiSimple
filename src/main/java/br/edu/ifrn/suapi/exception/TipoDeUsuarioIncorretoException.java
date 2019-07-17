package br.edu.ifrn.suapi.exception;

public class TipoDeUsuarioIncorretoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Tipo errado de vinculo do usuário com o SUAP";
	}
}
