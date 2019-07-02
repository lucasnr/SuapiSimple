package br.edu.ifrn.suapi.exception;

import br.edu.ifrn.suapi.ClienteSUAP;

/**
 * Exceção para ser lançada durante a construção de um {@link ClienteSUAP} caso
 * a conexão não possa ser estabelecida
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.0
 */
public class FalhaAoConectarComSUAPException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Exceção pai desta exceção
	 * 
	 * @since 1.0
	 */
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
