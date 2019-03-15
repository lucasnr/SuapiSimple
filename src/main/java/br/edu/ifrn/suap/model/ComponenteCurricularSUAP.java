package br.edu.ifrn.suap.model;

public class ComponenteCurricularSUAP {

	private String nome;
	private String codigo;
	private String sigla;

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
