package br.edu.ifrn.suapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe modelo que representa um vinculo de um servidor com o SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.0
 */

@Getter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=false)
public final class VinculoServidorSUAP extends VinculoSUAP{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O cargo do servidor com este vinculo
	 * 
	 * @since 1.0
	 */
	private String cargo;

	/**
	 * O setor do servidor com este vinculo no SUAP
	 * 
	 * @since 1.0
	 */
	@JsonProperty("setor_suap")
	private String setorSUAP;

	/**
	 * O setor do servidor com este vinculo no SIAPE
	 * 
	 * @since 1.0
	 */
	@JsonProperty("setor_siape")
	private String setorSIAPE;

	/**
	 * A jornada de trabalho do servidor com este vinculo
	 * 
	 * @since 1.0
	 */
	@JsonProperty("jornada_trabalho")
	private String jornadaTrabalho;


	/**
	 * A função do servidor com este vinculo
	 * 
	 * @since 1.0
	 */
	private String[] funcao;

	/**
	 * O e-mail do servidor com este vinculo
	 * 
	 * @since 1.0
	 */
	private String email;

	/**
	 * Os telefones institucionais do servidor com este vinculo
	 * 
	 * @since 1.0
	 */
	@JsonProperty("telefones_institucionais")
	private String[] telefonesInstitucionais;

	/**
	 * A categoria do servidor com este vinculo
	 * 
	 * @since 1.0
	 */
	private String categoria;

	/**
	 * A disciplina de ingresso do servidor com este vinculo
	 * 
	 * @since 1.0
	 */
	@JsonProperty("disciplina_ingresso")
	private String disciplinaIngresso;

	/**
	 * A URL da foto (75x100) no SUAP do servidor com este vinculo
	 * 
	 * @since 1.0
	 */
	@JsonProperty("url_foto_75x100")
	private String urlFoto;

}
