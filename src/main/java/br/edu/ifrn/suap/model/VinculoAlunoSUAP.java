package br.edu.ifrn.suap.model;

import com.google.gson.annotations.SerializedName;

/**
 * Classe modelo que representa um vinculo de um aluno com o SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.1
 * @version 1.1
 */
public class VinculoAlunoSUAP extends VinculoSUAP {

	/**
	 * O nome do curso do aluno com este vinculo
	 * 
	 * @since 1.1
	 */
	private String curso;

	/**
	 * A situação do aluno com este vinculo
	 * 
	 * @since 1.1
	 */
	private String situacao;

	/**
	 * A cota SISTEC do aluno com este vinculo
	 * 
	 * @since 1.1
	 */
	@SerializedName("cota_sistec")
	private String cotaSISTEC;

	/**
	 * A cota MEC do aluno com este vinculo
	 * 
	 * @since 1.1
	 */
	@SerializedName("cota_mec")
	private String cotaMEC;

	/**
	 * A situação sistemica do aluno com este vinculo
	 * 
	 * @since 1.1
	 */
	@SerializedName("situacao_sistemica")
	private String situacaoSistemica;

	/**
	 * Um valor booleano indicando se a matrícula do aluno com este vinculo é
	 * regular
	 * 
	 * @since 1.1
	 */
	@SerializedName("matricula_regular")
	private boolean matriculaRegular;

	/**
	 * A linha de pesquisa do aluno com este vinculo
	 * 
	 * @since 1.1
	 */
	@SerializedName("linha_pesquisa")
	private String linhaPesquisa;

	private VinculoAlunoSUAP() {
	}

	public String getCurso() {
		return curso;
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

	@Override
	public String toString() {
		return "Vinculo [matricula=" + matricula + ", nome=" + nome + ", curso=" + curso + ", campus=" + campus
				+ ", situacao=" + situacao + ", cotaSistec=" + cotaSISTEC + ", cotaMec=" + cotaMEC
				+ ", situacaoSistemica=" + situacaoSistemica + ", matriculaRegular=" + matriculaRegular
				+ ", linhaPesquisa=" + linhaPesquisa + ", curriculoLattes=" + curriculoLattes + "]";
	}

}
