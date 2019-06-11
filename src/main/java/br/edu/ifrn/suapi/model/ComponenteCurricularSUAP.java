package br.edu.ifrn.suapi.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe modelo de um componente curricular ou disciplina do SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.0
 */

@Getter
@ToString
@EqualsAndHashCode
public final class ComponenteCurricularSUAP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O nome deste componente curricular
	 * 
	 * @since 1.0
	 */
	private String nome;

	/**
	 * O código deste componente curricular no SUAP
	 * 
	 * @since 1.0
	 */
	private String codigo;

	/**
	 * A sigla deste componente curricular no SUAP
	 * 
	 * @since 1.0
	 */
	private String sigla;

}
