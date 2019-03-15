package br.edu.ifrn.suap.model;

import com.google.gson.annotations.SerializedName;

public class VinculoSUAP {

	private String matricula;
	private String nome;
	private String curso;
	private String campus;
	private String situacao;
	@SerializedName("cota_sistec")
	private String cotaSistec;
	@SerializedName("cota_mec")
	private String cotaMec;
	@SerializedName("situacao_sistemica")
	private String situacaoSistemica;
	@SerializedName("matricula_regular")
	private boolean matriculaRegular;
	@SerializedName("linha_pesquisa")
	private String linhaPesquisa;
	@SerializedName("curriculo_lattes")
	private String curriculoLattes;

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getCurso() {
		return curso;
	}

	public String getCampus() {
		return campus;
	}

	public String getSituacao() {
		return situacao;
	}

	public String getCotaSistec() {
		return cotaSistec;
	}

	public String getCotaMec() {
		return cotaMec;
	}

	public String getSituacaoSistemica() {
		return situacaoSistemica;
	}

	public boolean isMatriculaRegular() {
		return matriculaRegular;
	}

	public String getLinhaPesquisa() {
		return linhaPesquisa;
	}

	public String getCurriculoLattes() {
		return curriculoLattes;
	}

	@Override
	public String toString() {
		return "Vinculo [matricula=" + matricula + ", nome=" + nome + ", curso=" + curso + ", campus=" + campus
				+ ", situacao=" + situacao + ", cotaSistec=" + cotaSistec + ", cotaMec=" + cotaMec
				+ ", situacaoSistemica=" + situacaoSistemica + ", matriculaRegular=" + matriculaRegular
				+ ", linhaPesquisa=" + linhaPesquisa + ", curriculoLattes=" + curriculoLattes + "]";
	}

}
