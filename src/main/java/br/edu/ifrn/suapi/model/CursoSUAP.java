package br.edu.ifrn.suapi.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe modelo de um curso do SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @version 1.0
 * @since 1.0
 */

@Getter
@ToString(exclude= {"componentesCurriculares"})
@EqualsAndHashCode
public final class CursoSUAP implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * O código deste curso no SUAP
	 * 
	 * @since 1.0
	 */
	private String codigo;

	/**
	 * A descrição deste curso
	 * 
	 * @since 1.0
	 */
	private String descricao;

	/**
	 * A diretoria deste curso
	 * 
	 * @since 1.0
	 */
	private String diretoria;

	/**
	 * A carga horaria deste curso
	 * 
	 * @since 1.0
	 */
	@JsonProperty("carga_horaria")
	private String cargaHoraria;

	/**
	 * A natureza de participação deste curso no SUAP
	 * 
	 * @since 1.0
	 */
	@JsonProperty("natureza_participacao")
	private String naturezaParticipacao;

	/**
	 * O eixo deste curso no SUAP
	 * 
	 * @since 1.0
	 */
	private String eixo;

	/**
	 * A modalidade deste curso
	 * 
	 * @since 1.0
	 */
	private String modalidade;

	/**
	 * O nome do coordenador deste curso
	 * 
	 * @since 1.0
	 */
	private String coordenador;

	/**
	 * Os componentes curriculares deste curso
	 * 
	 * @see ComponenteCurricularSUAP
	 * 
	 * @since 1.0
	 */
	@JsonProperty("componentes_curriculares")
	private List<ComponenteCurricularSUAP> componentesCurriculares;
}
