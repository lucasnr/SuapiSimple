package br.edu.ifrn.suap.model;

import com.google.gson.annotations.SerializedName;

public class VinculoAlunoSUAP {

	private String matricula;
	
	private String nome;
	
	private String curso;
	
	private String campus;
	
	private String situacao;
	
	@SerializedName("cota_sistec")
	private String cotaSISTEC;
	
	@SerializedName("cota_mec")
	private String cotaMEC;
	
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

	public String getCotaSISTEC() {
		return cotaSISTEC;
	}

	public String getCotaMEC() {
		return cotaMEC;
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
				+ ", situacao=" + situacao + ", cotaSistec=" + cotaSISTEC + ", cotaMec=" + cotaMEC
				+ ", situacaoSistemica=" + situacaoSistemica + ", matriculaRegular=" + matriculaRegular
				+ ", linhaPesquisa=" + linhaPesquisa + ", curriculoLattes=" + curriculoLattes + "]";
	}

	
}
