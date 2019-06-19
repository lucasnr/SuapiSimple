package br.edu.ifrn.suapi.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

import br.edu.ifrn.suapi.ClienteSUAP;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe pai de todos os usuários com algum vinculo no SUAP
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.0
 */

@Getter
@ToString(exclude= {"clienteSUAP"})
@EqualsAndHashCode
public class UsuarioSUAP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * @since 1.0
	 */
	@SerializedName("cpf")
	protected String CPF;

	/**
	 * RG deste usuário
	 * 
	 * @since 1.0
	 */
	@SerializedName("rg")
	protected String RG;

	/**
	 * Array de {@link String} contendo a filiação deste usuário
	 * 
	 * @since 1.0
	 */
	protected String[] filiacao;

	/**
	 * Data de nascimento em {@link String} deste usuário
	 * 
	 * @since 1.0
	 */
	@SerializedName("data_nascimento")
	protected Date dataDeNascimento;

	/**
	 * Naturalidade deste usuário
	 * 
	 * @since 1.0
	 */
	protected String naturalidade;

	/**
	 * Tipo sanguíneo deste usuário
	 * 
	 * @since 1.0
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
	 * @since 1.0
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
	protected transient ClienteSUAP clienteSUAP;

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

	/**
	 * Atribui o cliente SUAP se e somente se o objeto de {@link ClienteSUAP} deste
	 * usuario está nulo
	 * 
	 * @param clienteSUAP O cliente a ser definido, settado
	 */
	public void defineClienteSUAP(ClienteSUAP clienteSUAP) {
		// garante que só será atribuido uma única vez
		if (this.clienteSUAP == null)
			this.clienteSUAP = clienteSUAP;
	}

}
