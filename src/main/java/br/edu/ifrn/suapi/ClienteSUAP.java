package br.edu.ifrn.suapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import br.edu.ifrn.suapi.exception.FalhaAoConectarComSUAPException;
import br.edu.ifrn.suapi.model.CursoSUAP;
import br.edu.ifrn.suapi.model.UsuarioSUAP;
import br.edu.ifrn.suapi.util.JSONHandler;

/**
 * Classe que representa um cliente do SUAP. Esta classe é responsável por fazer
 * as requisições ao servidor do SUAP e retornar as informações, vindas em JSON,
 * em objetos Java
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.2
 */
public class ClienteSUAP {

	/**
	 * O TOKEN deste cliente
	 * 
	 * @since 1.0
	 */
	private final String TOKEN;

	/**
	 * A autenticação deste cliente, usada para realizar requisições ao SUAP
	 * 
	 * @since 1.0
	 */
	private final String AUTH;

	/**
	 * Construtor único, que define um TOKEN e uma Autenticação realizando uma
	 * requisição POST ao servidor do SUAP com as credenciais recebidas
	 * 
	 * @param matricula
	 *            A matricula do usuário
	 * @param senha
	 *            A senha do SUAP deste usuário
	 * @throws FalhaAoConectarComSUAPException
	 * @since 1.0
	 */
	public ClienteSUAP(String matricula, String senha) throws FalhaAoConectarComSUAPException {

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();

			String respostaJSON = requisitaToken(matricula, senha, httpClient);
			String atributoToken = pegaAtributoTokenDaRespostaJSON(respostaJSON);

			this.TOKEN = atributoToken;
			this.AUTH = criaStringDeAutenticacao();
			httpClient.close();
		} catch (Exception e) {
			throw new FalhaAoConectarComSUAPException(e);
		}
	}

	/**
	 * Retorna o atributo <strong>token</strong> do JSON recebido como parametro
	 * 
	 * @param respostaJSON
	 *            Um JSON em forma de {@link String}
	 * @return O atributo <strong>token</strong> deste JSON
	 */
	private String pegaAtributoTokenDaRespostaJSON(String respostaJSON) {
		String atributoToken = null;
		Gson gson = new Gson();
		Map<?, ?> jsonMap = gson.fromJson(respostaJSON, Map.class);
		if (jsonMap.get("token") != null) {
			atributoToken = jsonMap.get("token").toString();
		}
		return atributoToken;
	}

	/**
	 * Faz uma requisição ao SUAP usando as credenciais de matrícula e senha e
	 * retorna a resposta do SUAP, um JSON contendo o atributo
	 * <strong>token</strong>
	 * 
	 * @param matricula
	 *            A matricula do usuário
	 * @param senha
	 *            A senha do usuário
	 * @param httpClient
	 *            Um cliente HTTP para realizar a requisição
	 * @return Uma {@link String} que representa um JSON com atributo único, o token
	 * @since 1.2
	 */
	private String requisitaToken(String matricula, String senha, HttpClient httpClient)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		HttpPost doPost = criaUmaRequisicaoPostParaUrlDoToken(matricula, senha);
		ResponseHandler<String> responseHandler = new JSONHandler();
		String respostaJSON = httpClient.execute(doPost, responseHandler);
		return respostaJSON;
	}

	/**
	 * Cria uma string de autenticação com o SUAP. O padrão utilizado pelo SUAP é:
	 * <strong>JWT <i>token</i></strong>
	 * 
	 * @return Uma {@link String} que representa a autenticação deste usuário
	 *         utilizado durante as requisições
	 * @since 1.2
	 */
	private String criaStringDeAutenticacao() {
		return "JWT " + this.TOKEN;
	}

	/**
	 * Cria uma string de autenticação com o SUAP. O padrão utilizado pelo SUAP é:
	 * <strong>JWT <i>token</i></strong>
	 * 
	 * @param matricula
	 *            A matrícula do usuário
	 * @param senha
	 *            a senha do usuário
	 * @return Um objeto de {@link HttpPost} que representa uma requisição a URL de
	 *         autenticação da API REST do SUAP
	 * @since 1.2
	 */
	private HttpPost criaUmaRequisicaoPostParaUrlDoToken(String matricula, String senha)
			throws UnsupportedEncodingException {
		String urlDeAutenticacao = "https://suap.ifrn.edu.br/api/v2/autenticacao/token/";
		HttpPost requisicaoPost = new HttpPost(urlDeAutenticacao);
		List<NameValuePair> parametros = criaParametrosDeMatriculaESenha(matricula, senha);
		requisicaoPost.setEntity(new UrlEncodedFormEntity(parametros, "UTF-8"));
		return requisicaoPost;
	}

	/**
	 * Cria as credenciais de autenticação para realizar uma requisição à API REST
	 * do SUAP
	 * 
	 * @param matricula
	 *            A matrícula do usuário
	 * @param senha
	 *            a senha do usuário
	 * @return Uma lista de par name e value, necessária para ser passada como
	 *         parametros de autentição para a requisição
	 * @since 1.2
	 */
	private List<NameValuePair> criaParametrosDeMatriculaESenha(String matricula, String senha) {
		List<NameValuePair> parametros = new ArrayList<NameValuePair>(2);
		parametros.add(new BasicNameValuePair("username", matricula));
		parametros.add(new BasicNameValuePair("password", senha));
		return parametros;
	}

	/**
	 * Retorna um objeto java parametizado do cliente, sendo o seu tipo o que for
	 * enviado como parâmetro da chamada (Aluno, Servidor)
	 *
	 * @param clazz
	 *            Classe que herde de UsuarioSUAP
	 * @return AlunoSUAP ou ServidorSUAP
	 * @since 1.1
	 */
	@SuppressWarnings("unchecked")
	public <T extends UsuarioSUAP> T getUsuario(Class<T> clazz) {
		if (!isAutenticado()) {
			return null;
		}

		String url = "https://suap.ifrn.edu.br/api/v2/minhas-informacoes/meus-dados/";
		String meusDados = doGet(url);
		Gson gson = new Gson();
		UsuarioSUAP usuario = gson.fromJson(meusDados, clazz);
		usuario.ajustaURL();
		usuario.defineClienteSUAP(this);

		return (T) usuario;
	}

	/**
	 * Faz uma requisição ao banco de dados do SUAP e retorna um objeto java de
	 * Curso
	 * 
	 * @param codigo
	 *            O codigo do curso no SUAP
	 * @return O objeto de CursoSUAP
	 */
	public final CursoSUAP getCurso(String codigo) {
		String url = "https://suap.ifrn.edu.br/api/v2/edu/cursos/" + codigo + "/";
		String cursoJSON = doGet(url);
		Gson gson = new Gson();
		CursoSUAP curso = gson.fromJson(cursoJSON, CursoSUAP.class);
		return curso;
	}

	/**
	 * Faz uma requisição do tipo GET à alguma URL
	 * 
	 * @since 1.0
	 * @param url
	 *            URL à qual se deseja fazer uma requisição GET
	 * @return A {@link String} contendo a resposta, em JSON, da requisição
	 */
	protected String doGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);

			httpget.addHeader("Accept", "application/json");
			httpget.addHeader("X-CSRFToken", TOKEN);
			httpget.addHeader("Authorization", AUTH);

			ResponseHandler<String> responseHandler = new JSONHandler();
			String response = httpclient.execute(httpget, responseHandler);
			return response;
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * Verifica se o cliente está autenticado ou não, ou seja, se na construção do
	 * objeto, as credenciais foram informadas corretamente.
	 * 
	 * @since 1.0
	 * @return true se o usuário possue um token de autenticação válido <br>
	 *         false se a autenticação não teve sucesso
	 */
	public boolean isAutenticado() {
		return this.TOKEN != null;
	}
	
	public String getTOKEN() {
		return TOKEN;
	}

}
