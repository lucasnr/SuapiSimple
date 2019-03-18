package br.edu.ifrn.suap.model;

import com.google.gson.annotations.SerializedName;

import br.edu.ifrn.suap.ClienteSUAP;

public class UsuarioSUAP {

	@SerializedName("id")
	protected Integer suapId;
	protected String matricula;
	protected String email;
	@SerializedName("nome_usual")
	protected String nomeUsual;
	@SerializedName("url_foto_75x100")
	protected String urlFoto;
	@SerializedName("tipo_vinculo")
	protected String tipoVinculo;
	protected VinculoSUAP vinculo;

	protected ClienteSUAP clienteSUAP;

	public UsuarioSUAP(ClienteSUAP clienteSUAP) {
		this.clienteSUAP = clienteSUAP;
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

	public ClienteSUAP getClienteSUAP() {
		return clienteSUAP;
	}

	public void setClienteSUAP(ClienteSUAP clienteSUAP) {
		if (this.clienteSUAP == null) // garante que só será atribuido uma única vez
			this.clienteSUAP = clienteSUAP;
	}

	@Override
	public String toString() {
		return "SUAPUsuario [suapId=" + suapId + ", matricula=" + matricula + ", email=" + email + ", nomeUsual="
				+ nomeUsual + ", urlFoto=" + urlFoto + ", tipoVinculo=" + tipoVinculo + "]";
	}
	
}
