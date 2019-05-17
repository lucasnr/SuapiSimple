package br.edu.ifrn.suap.model;

/**
 * Classe modelo de um usuário do SUAP com vinculo do tipo Servidor
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.1
 * @version 1.1
 */
public class ServidorSUAP extends UsuarioSUAP {

	/**
	 * O vinculo deste servidor com o SUAP
	 * 
	 * @see VinculoServidorSUAP
	 * 
	 * @since 1.1
	 */
	private VinculoServidorSUAP vinculo;

	private ServidorSUAP() {
	}

	public VinculoServidorSUAP getVinculo() {
		return vinculo;
	}
}
