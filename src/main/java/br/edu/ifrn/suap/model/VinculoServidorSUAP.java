package br.edu.ifrn.suap.model;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

public class VinculoServidorSUAP {

	private String matricula;

	private String nome;

	private String cargo;

	@SerializedName("setor_suap")
	private String setorSUAP;

	@SerializedName("setor_siape")
	private String setorSIAPE;

	@SerializedName("jornada_trabalho")
	private String jornadaTrabalho;

	private String[] funcao;

	private String campus;

	private String email;

	@SerializedName("telefones_institucionais")
	private String[] telefonesInstitucionais;

	private String categoria;

	@SerializedName("disciplina_ingresso")
	private String disciplinaIngresso;

	@SerializedName("url_foto_75x100")
	private String urlFoto;

	@SerializedName("curriculo_lattes")
	private String curriculoLattes;

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getCargo() {
		return cargo;
	}

	public String getSetorSUAP() {
		return setorSUAP;
	}

	public String getSetorSIAPE() {
		return setorSIAPE;
	}

	public String getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public String[] getFuncao() {
		return funcao;
	}

	public String getCampus() {
		return campus;
	}

	public String getEmail() {
		return email;
	}

	public String[] getTelefonesInstitucionais() {
		return telefonesInstitucionais;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getDisciplinaIngresso() {
		return disciplinaIngresso;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public String getCurriculoLattes() {
		return curriculoLattes;
	}

	@Override
	public String toString() {
		return "VinculoServidorSUAP [matricula=" + matricula + ", nome=" + nome + ", cargo=" + cargo + ", setorSUAP="
				+ setorSUAP + ", setorSiape=" + setorSIAPE + ", jornadaTrabalho=" + jornadaTrabalho + ", funcao="
				+ Arrays.toString(funcao) + ", campus=" + campus + ", email=" + email + ", telefonesInstitucionais="
				+ Arrays.toString(telefonesInstitucionais) + ", categoria=" + categoria + ", disciplinaIngresso="
				+ disciplinaIngresso + ", urlFoto=" + urlFoto + ", curriculoLattes=" + curriculoLattes + "]";
	}

}
