package br.edu.ifrn.suapi.model;

/**
 * Classe modelo de um componente curricular ou disciplina do SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.0
 */
public class ComponenteCurricularSUAP {

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
	
	private ComponenteCurricularSUAP() {
	}

	public String getNome() {
		return nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getSigla() {
		return sigla;
	}

	@Override
	public String toString() {
		return "ComponenteCurricular [nome=" + nome + ", codigo=" + codigo + ", sigla=" + sigla + "]";
	}

}
