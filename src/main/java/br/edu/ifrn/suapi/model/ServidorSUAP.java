package br.edu.ifrn.suapi.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe modelo de um usuário do SUAP com vinculo do tipo Servidor
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.1
 * @version 1.1
 */

@Getter
@ToString
@EqualsAndHashCode(callSuper=false)
public final class ServidorSUAP extends UsuarioSUAP {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * O vinculo deste servidor com o SUAP
	 * 
	 * @see VinculoServidorSUAP
	 * @since 1.1
	 */
	private VinculoServidorSUAP vinculo;
}
