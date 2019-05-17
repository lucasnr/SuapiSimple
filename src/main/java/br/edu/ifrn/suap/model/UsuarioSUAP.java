package br.edu.ifrn.suap.model;

import java.util.Arrays;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

import br.edu.ifrn.suap.ClienteSUAP;

/**
 * Classe pai de todos os usuários com algum vinculo no SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.1
 */
public class UsuarioSUAP {

	/**
	 * ID único deste usuário no SUAP
	 * 
	 * @since 1.0
	 */
	@SerializedName("id")
	protected Integer suapId;

	/**
	 * Matrícula única deste usuário no SUAP
	 * 
	 * @since 1.0
	 */
	protected String matricula;

	/**
	 * Nome usual deste usuário usado no SUAP frequentemente
	 * 
	 * @since 1.0
	 */
	@SerializedName("nome_usual")
	protected String nomeUsual;

	/**
	 * CPF deste usuário
	 * 
	 * @since 1.1
	 */
	@SerializedName("cpf")
	protected String CPF;

	/**
	 * RG deste usuário
	 * 
	 * @since 1.1
	 */
	@SerializedName("rg")
	protected String RG;

	/**
	 * Array de {@link String} contendo a filiação deste usuário
	 * 
	 * @since 1.1
	 */
	protected String[] filiacao;

	/**
	 * Data de nascimento em {@link String} deste usuário
	 * 
	 * @since 1.1
	 */
	@SerializedName("data_nascimento")
	protected Date dataDeNascimento;

	/**
	 * Naturalidade deste usuário
	 * 
	 * @since 1.1
	 */
	protected String naturalidade;

	/**
	 * Tipo sanguíneo deste usuário
	 * 
	 * @since 1.1
	 */
	@SerializedName("tipo_sanguineo")
	protected String tipoSanguineo;

	/**
	 * Email academico deste usuário
	 * 
	 * @since 1.0
	 */
	protected String email;

	/**
	 * URL da foto (75x100) deste usuário no servidor do SUAP
	 * 
	 * @since 1.0
	 */
	@SerializedName("url_foto_75x100")
	protected String urlFoto;

	/**
	 * URL da foto (150x200) deste usuário no servidor do SUAP
	 * 
	 * @since 1.1
	 */
	@SerializedName("url_foto_150x200")
	protected String urlFotoGrande;

	/**
	 * {@link String} com o tipo de vinculo que este usuário possui com o SUAP
	 * 
	 * @since 1.0
	 */
	@SerializedName("tipo_vinculo")
	protected String tipoVinculo;

	/**
	 * O cliente deste usuário com o SUAP, onde o token se encontra e por onde as
	 * requisições são realizadas
	 * 
	 * @since 1.0
	 */
	protected ClienteSUAP clienteSUAP;

	protected UsuarioSUAP() {
	}

	/**
	 * Ajusta as URL para caminhos absolutos
	 * 
	 * @since 1.0
	 */
	public void ajustaURL() {
		String inicio = "https://suap.ifrn.edu.br";
		if (!this.urlFoto.startsWith(inicio)) {
			this.urlFoto = inicio.concat(urlFoto);
		}
		if (!this.urlFotoGrande.startsWith(inicio)) {
			this.urlFotoGrande = inicio.concat(urlFotoGrande);
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

	public String getCPF() {
		return CPF;
	}

	public String getRG() {
		return RG;
	}

	public String[] getFiliacao() {
		return filiacao;
	}

	public Date getDataDeNascimento() {
		return dataDeNascimento;
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

	/**
	 * Atribui o cliente SUAP se e somente se o objeto de {@link ClienteSUAP} deste
	 * usuario está nulo
	 * 
	 * @param clienteSUAP O cliente a ser definido, settado
	 * 
	 */
	public void defineClienteSUAP(ClienteSUAP clienteSUAP) {
		// garante que só será atribuido uma única vez
		if (this.clienteSUAP == null)
			this.clienteSUAP = clienteSUAP;
	}

	@Override
	public String toString() {
		return "UsuarioSUAP [suapId=" + suapId + ", matricula=" + matricula + ", nomeUsual=" + nomeUsual + ", cpf="
				+ CPF + ", rg=" + RG + ", filiacao=" + Arrays.toString(filiacao) + ", dataNascimento="
				+ dataDeNascimento + ", naturalidade=" + naturalidade + ", tipoSanguineo=" + tipoSanguineo + ", email="
				+ email + ", urlFoto=" + urlFoto + ", urlFotoGrande=" + urlFotoGrande + ", tipoVinculo=" + tipoVinculo
				+ "]";
	}

}
