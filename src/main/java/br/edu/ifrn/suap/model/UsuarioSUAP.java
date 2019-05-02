package br.edu.ifrn.suap.model;

import java.util.Arrays;

import com.google.gson.annotations.SerializedName;

import br.edu.ifrn.suap.ClienteSUAP;

public class UsuarioSUAP {

	@SerializedName("id")
	protected Integer suapId;

	protected String matricula;

	@SerializedName("nome_usual")
	protected String nomeUsual;

	protected String cpf;

	protected String rg;

	protected String[] filiacao;

	@SerializedName("data_nascimento")
	protected String dataNascimento;

	protected String naturalidade;

	@SerializedName("tipo_sanguineo")
	protected String tipoSanguineo;

	protected String email;

	@SerializedName("url_foto_75x100")
	protected String urlFoto;

	@SerializedName("url_foto_150x200")
	protected String urlFotoGrande;

	@SerializedName("tipo_vinculo")
	protected String tipoVinculo;

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

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String[] getFiliacao() {
		return filiacao;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public String getTipoSanguineo() {
		return tipoSanguineo;
	}

	public String getUrlFotoGrande() {
		return urlFotoGrande;
	}

	public ClienteSUAP getClienteSUAP() {
		return clienteSUAP;
	}

	public void defineClienteSUAP(ClienteSUAP clienteSUAP) {
		// garante que só será atribuido uma única vez
		this.clienteSUAP = clienteSUAP;
	}

	@Override
	public String toString() {
		return "UsuarioSUAP [suapId=" + suapId + ", matricula=" + matricula + ", nomeUsual=" + nomeUsual + ", cpf="
				+ cpf + ", rg=" + rg + ", filiacao=" + Arrays.toString(filiacao) + ", dataNascimento=" + dataNascimento
				+ ", naturalidade=" + naturalidade + ", tipoSanguineo=" + tipoSanguineo + ", email=" + email
				+ ", urlFoto=" + urlFoto + ", urlFotoGrande=" + urlFotoGrande + ", tipoVinculo=" + tipoVinculo + "]";
	}

}
