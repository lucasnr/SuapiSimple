package br.edu.ifrn.suapi.exception;

public class FalhaAoConectarComSUAPException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Exception parentException;

	public FalhaAoConectarComSUAPException(Exception parentException) {
		this.parentException = parentException;
	}

	@Override
	public String getMessage() {
		return "Não foi possivel estabelecer uma conexão com o SUAP"; 
	}
	
	@Override
	public synchronized Throwable getCause() {
		return parentException.getCause();
	}
	
}
