package br.edu.ifrn.suapi.exception;

public class CursoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public CursoNaoEncontradoException(String codigo) {
		this.message = "Nenhum curso encontrado para o código: " + codigo;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
