package br.edu.ifrn.suap.model;

import java.util.Collections;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class CursoSUAP {

	private String codigo;
	private String descricao;
	private String diretoria;
	@SerializedName("carga_horaria")
	private String cargaHoraria;
	@SerializedName("natureza_participacao")
	private String naturezaParticipacao;
	private String eixo;
	private String modalidade;
	private String cordenador;
	@SerializedName("componentes_curriculares")
	private List<ComponenteCurricularSUAP> componentesCurriculares;

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

	public String getCordenador() {
		return cordenador;
	}

	public List<ComponenteCurricularSUAP> getComponentesCurriculares() {
		return Collections.unmodifiableList(componentesCurriculares);
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", descricao=" + descricao + ", diretoria=" + diretoria + ", cargaHoraria="
				+ cargaHoraria + ", naturezaParticipacao=" + naturezaParticipacao + ", eixo=" + eixo + ", modalidade="
				+ modalidade + ", cordenador=" + cordenador + ", componentesCurriculares=" + componentesCurriculares
				+ "]";
	}

}
