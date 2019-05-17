package br.edu.ifrn.suapi.model;

import java.util.Collections;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Classe modelo de um curso do SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @version 1.0
 * @since 1.0
 */
public class CursoSUAP {

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
	@SerializedName("carga_horaria")
	private String cargaHoraria;

	/**
	 * A natureza de participação deste curso no SUAP
	 * 
	 * @since 1.0
	 */
	@SerializedName("natureza_participacao")
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
	@SerializedName("componentes_curriculares")
	private List<ComponenteCurricularSUAP> componentesCurriculares;
	
	private CursoSUAP() {
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getDiretoria() {
		return diretoria;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public String getNaturezaParticipacao() {
		return naturezaParticipacao;
	}

	public String getEixo() {
		return eixo;
	}

	public String getModalidade() {
		return modalidade;
	}

	public String getCoordenador() {
		return coordenador;
	}

	public List<ComponenteCurricularSUAP> getComponentesCurriculares() {
		return Collections.unmodifiableList(componentesCurriculares);
	}

	@Override
	public String toString() {
		return "CursoSUAP [codigo=" + codigo + ", descricao=" + descricao + ", diretoria=" + diretoria
				+ ", cargaHoraria=" + cargaHoraria + ", naturezaParticipacao=" + naturezaParticipacao + ", eixo=" + eixo
				+ ", modalidade=" + modalidade + ", coordenador=" + coordenador + "]";
	}

}
