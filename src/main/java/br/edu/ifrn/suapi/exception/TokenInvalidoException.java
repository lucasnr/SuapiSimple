package br.edu.ifrn.suapi.exception;

public class TokenInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "O token informado não é válido";
	}
}
