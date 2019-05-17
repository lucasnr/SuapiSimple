package br.edu.ifrn.suap.model;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

/**
 * Classe modelo que representa um vinculo de um servidor com o SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.1
 * @version 1.1
 */
public class VinculoServidorSUAP extends VinculoSUAP{


	/**
	 * O cargo do servidor com este vinculo
	 * 
	 * @since 1.1
	 */
	private String cargo;

	/**
	 * O setor do servidor com este vinculo no SUAP
	 * 
	 * @since 1.1
	 */
	@SerializedName("setor_suap")
	private String setorSUAP;

	/**
	 * O setor do servidor com este vinculo no SIAPE
	 * 
	 * @since 1.1
	 */
	@SerializedName("setor_siape")
	private String setorSIAPE;

	/**
	 * A jornada de trabalho do servidor com este vinculo
	 * 
	 * @since 1.1
	 */
	@SerializedName("jornada_trabalho")
	private String jornadaTrabalho;


	/**
	 * A função do servidor com este vinculo
	 * 
	 * @since 1.1
	 */
	private String[] funcao;

	/**
	 * O e-mail do servidor com este vinculo
	 * 
	 * @since 1.1
	 */
	private String email;

	/**
	 * Os telefones institucionais do servidor com este vinculo
	 * 
	 * @since 1.1
	 */
	@SerializedName("telefones_institucionais")
	private String[] telefonesInstitucionais;

	/**
	 * A categoria do servidor com este vinculo
	 * 
	 * @since 1.1
	 */
	private String categoria;

	/**
	 * A disciplina de ingresso do servidor com este vinculo
	 * 
	 * @since 1.1
	 */
	@SerializedName("disciplina_ingresso")
	private String disciplinaIngresso;

	/**
	 * A URL da foto (75x100) no SUAP do servidor com este vinculo
	 * 
	 * @since 1.1
	 */
	@SerializedName("url_foto_75x100")
	private String urlFoto;

	private VinculoServidorSUAP() {
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

	@Override
	public String toString() {
		return "VinculoServidorSUAP [matricula=" + matricula + ", nome=" + nome + ", cargo=" + cargo + ", setorSUAP="
				+ setorSUAP + ", setorSiape=" + setorSIAPE + ", jornadaTrabalho=" + jornadaTrabalho + ", funcao="
				+ Arrays.toString(funcao) + ", campus=" + campus + ", email=" + email + ", telefonesInstitucionais="
				+ Arrays.toString(telefonesInstitucionais) + ", categoria=" + categoria + ", disciplinaIngresso="
				+ disciplinaIngresso + ", urlFoto=" + urlFoto + ", curriculoLattes=" + curriculoLattes + "]";
	}

}
