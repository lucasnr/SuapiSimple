package br.edu.ifrn.suap.model;

import com.google.gson.annotations.SerializedName;

import br.edu.ifrn.suap.SUAPClient;

public class UsuarioSUAP {

	@SerializedName("id")
	private Integer suapId;
	private String matricula;
	private String email;
	@SerializedName("nome_usual")
	private String nomeUsual;
	@SerializedName("url_foto_75x100")
	private String urlFoto;
	@SerializedName("tipo_vinculo")
	private String tipoVinculo;
	private VinculoSUAP vinculo;
	private CursoSUAP curso;

	private SUAPClient suapClient;

	public UsuarioSUAP(SUAPClient suapClient) {
		super();
		this.suapClient = suapClient;
	}

	public void ajustaURL() {
		if (!this.urlFoto.startsWith("https://suap.ifrn.edu.br")) {
			this.urlFoto = "https://suap.ifrn.edu.br" + urlFoto;
		}
	}

	public Integer getSuapId() {
		return suapId;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getEmail() {
		return email;
	}

	public String getNomeUsual() {
		return nomeUsual;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public String getTipoVinculo() {
		return tipoVinculo;
	}

	public VinculoSUAP getVinculo() {
		return vinculo;
	}

	public SUAPClient getSuapClient() {
		return suapClient;
	}

	public void setSuapClient(SUAPClient suapClient) {
		if (this.suapClient == null)
			this.suapClient = suapClient;
	}

	public CursoSUAP getCurso() {
		if (this.curso == null) {
			// O código do curso está presente na matricula
			// Ex: 20161164010023 -> 20161(16401)0023
			String cursoId = matricula.substring(5, 10);
			this.curso = this.suapClient.getCurso(cursoId);
		}

		return this.curso;
	}

	@Override
	public String toString() {
		return "SUAPUsuario [suapId=" + suapId + ", matricula=" + matricula + ", email=" + email + ", nomeUsual="
				+ nomeUsual + ", urlFoto=" + urlFoto + ", tipoVinculo=" + tipoVinculo + "]";
	}

}
