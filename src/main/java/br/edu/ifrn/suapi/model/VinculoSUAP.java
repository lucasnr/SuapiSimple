package br.edu.ifrn.suapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Classe pai de todos os tipos de vinculo com o SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.1
 * @version 1.1
 */
public class VinculoSUAP {


	/**
	 * A matrícula do usuário com este vinculo com o SUAP
	 * 
	 * @since 1.1
	 */
	protected String matricula;
	

	/**
	 * O nome do usuário com este vinculo com o SUAP
	 * 
	 * @since 1.1
	 */
	protected String nome;

	/**
	 * O campus deste vinculo com o SUAP
	 * 
	 * @since 1.1
	 */
	protected String campus;

	/**
	 * O curriculo lattes do usuário com este vinculo com o SUAP
	 * 
	 * @since 1.1
	 */
	@SerializedName("curriculo_lattes")
	protected String curriculoLattes;

	protected VinculoSUAP() {
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getCampus() {
		return campus;
	}

	public String getCurriculoLattes() {
		return curriculoLattes;
	}

	@Override
	public String toString() {
		return "VinculoSUAP [matricula=" + matricula + ", nome=" + nome + ", campus=" + campus + ", curriculoLattes="
				+ curriculoLattes + "]";
	}

}
