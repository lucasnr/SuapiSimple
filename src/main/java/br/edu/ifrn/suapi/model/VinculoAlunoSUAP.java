package br.edu.ifrn.suapi.model;

import com.google.gson.annotations.SerializedName;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe modelo que representa um vinculo de um aluno com o SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.0
 */

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
public final class VinculoAlunoSUAP extends VinculoSUAP {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O nome do curso do aluno com este vinculo
	 * 
	 * @since 1.0
	 */
	private String curso;

	/**
	 * A situação do aluno com este vinculo
	 * 
	 * @since 1.0
	 */
	private String situacao;

	/**
	 * A cota SISTEC do aluno com este vinculo
	 * 
	 * @since 1.0
	 */
	@SerializedName("cota_sistec")
	private String cotaSISTEC;

	/**
	 * A cota MEC do aluno com este vinculo
	 * 
	 * @since 1.0
	 */
	@SerializedName("cota_mec")
	private String cotaMEC;

	/**
	 * A situação sistemica do aluno com este vinculo
	 * 
	 * @since 1.0
	 */
	@SerializedName("situacao_sistemica")
	private String situacaoSistemica;

	/**
	 * Um valor booleano indicando se a matrícula do aluno com este vinculo é
	 * regular
	 * 
	 * @since 1.0
	 */
	@SerializedName("matricula_regular")
	private boolean matriculaRegular;

	/**
	 * A linha de pesquisa do aluno com este vinculo
	 * 
	 * @since 1.0
	 */
	@SerializedName("linha_pesquisa")
	private String linhaPesquisa;
}
