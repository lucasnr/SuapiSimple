package br.edu.ifrn.suapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import br.edu.ifrn.suapi.exception.CredenciaisIncorretasException;
import br.edu.ifrn.suapi.exception.FalhaAoConectarComSUAPException;
import br.edu.ifrn.suapi.exception.TokenInvalidoException;
import br.edu.ifrn.suapi.model.AlunoSUAP;
import br.edu.ifrn.suapi.model.CursoSUAP;
import br.edu.ifrn.suapi.model.ServidorSUAP;
import br.edu.ifrn.suapi.model.TokenSUAP;
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
public final class ClienteSUAP {


	private static final String PREFIX = "https://suap.ifrn.edu.br/api/v2/";

	private static final String AUTENTICACAO_TOKEN_URL = PREFIX + "autenticacao/token/";

	private static final String MEUS_DADOS_URL = PREFIX + "minhas-informacoes/meus-dados/";
	
	private static final String VALIDA_TOKEN_URL = PREFIX + "autenticacao/token/verify/";
	
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
	 * @param matricula A matricula do usuário
	 * @param senha     A senha do SUAP deste usuário
	 * @throws FalhaAoConectarComSUAPException
	 * @throws CredenciaisIncorretasException
	 * @since 1.0
	 */
	public ClienteSUAP(String matricula, String senha)
			throws FalhaAoConectarComSUAPException, CredenciaisIncorretasException {

		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();

			TokenSUAP token = requisitaToken(matricula, senha, httpClient);
			this.TOKEN = token.getToken();
			if (!this.isAutenticado())
				throw new CredenciaisIncorretasException();

			this.AUTH = criaStringDeAutenticacao();

			httpClient.close();
		} catch (IOException e) {
			throw new FalhaAoConectarComSUAPException(e);
		}
	}

	public ClienteSUAP(String token) throws TokenInvalidoException, FalhaAoConectarComSUAPException {
		try {
			boolean isValido = isTokenValido(token);
			if (! isValido) {				
				throw new TokenInvalidoException();
			}
		} catch (IOException e) {
			throw new FalhaAoConectarComSUAPException(e);
		}
		
		this.TOKEN = token;
		this.AUTH = criaStringDeAutenticacao();
	}
	
	private boolean isTokenValido(String token) throws IOException {
		
		if (token == null || token.isEmpty()) {
			return false;
		}

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(VALIDA_TOKEN_URL);
		
		List<NameValuePair> parametros = new ArrayList<NameValuePair>();
		parametros.add(new BasicNameValuePair("token", token));
		httpPost.setEntity(new UrlEncodedFormEntity(parametros, "UTF-8"));
		
		String respostaJSON = executaRequisicaoERetornaJSON(httpClient, httpPost);
		httpClient.close();
		
		Gson gson = new Gson();
		TokenSUAP tokenSUAP = gson.fromJson(respostaJSON, TokenSUAP.class);
		
		if (tokenSUAP == null || tokenSUAP.getToken() == null || tokenSUAP.getToken().contains("Error")) {
			return false;
		}
		return true;
	}

	/**
	 * Faz uma requisição ao SUAP usando as credenciais de matrícula e senha e
	 * retorna o token deste usuário no SUAP
	 * 
	 * @param matricula  A matricula do usuário
	 * @param senha      A senha do usuário
	 * @param httpClient Um cliente HTTP para realizar a requisição
	 * @return Um {@link TokenSUAP} do SUAP
	 * @since 1.2
	 */
	private TokenSUAP requisitaToken(String matricula, String senha, HttpClient httpClient)
			throws UnsupportedEncodingException, IOException, ClientProtocolException {
		HttpPost doPost = criaUmaRequisicaoParaPegarOToken(matricula, senha);
		String respostaJSON = executaRequisicaoERetornaJSON(httpClient, doPost);

		Gson gson = new Gson();
		TokenSUAP token = gson.fromJson(respostaJSON, TokenSUAP.class);
		return token;
	}

	private HttpPost criaUmaRequisicaoParaPegarOToken(String matricula, String senha) throws UnsupportedEncodingException {
		HttpPost doPost = new HttpPost(AUTENTICACAO_TOKEN_URL);
		List<NameValuePair> parametros = criaParametrosDeMatriculaESenha(matricula, senha);
		doPost.setEntity(new UrlEncodedFormEntity(parametros, "UTF-8"));
		return doPost;
	}
	
	private String executaRequisicaoERetornaJSON(HttpClient httpClient, HttpRequestBase doRequest)
			throws IOException, ClientProtocolException {
		ResponseHandler<String> responseHandler = new JSONHandler();
		String respostaJSON = httpClient.execute(doRequest, responseHandler);
		return respostaJSON;
	}

	/**
	 * Cria as credenciais de autenticação para realizar uma requisição à API REST
	 * do SUAP
	 * 
	 * @param matricula A matrícula do usuário
	 * @param senha     A senha do usuário
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
	 * @param clazz Classe que herde de {@link UsuarioSUAP}
	 * @return {@link AlunoSUAP} ou {@link ServidorSUAP}
	 * @since 1.1
	 */
	@SuppressWarnings("unchecked")
	public <T extends UsuarioSUAP> T getUsuario(Class<T> clazz) {
		if (!isAutenticado()) {
			return null;
		}

		String meusDados = doGet(MEUS_DADOS_URL);
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
	 * @param codigo O codigo do curso no SUAP
	 * @return O objeto de CursoSUAP
	 */
	public CursoSUAP getCurso(String codigo) {
		if (! this.isAutenticado()) {
			return null;
		}
		String url = PREFIX + "edu/cursos/" + codigo + "/";
		String cursoJSON = doGet(url);
		Gson gson = new Gson();
		CursoSUAP curso = gson.fromJson(cursoJSON, CursoSUAP.class);
		return curso;
	}

	/**
	 * Faz uma requisição do tipo GET à alguma URL
	 * 
	 * @since 1.0
	 * @param url URL à qual se deseja fazer uma requisição GET
	 * @return A {@link String} contendo a resposta, em JSON, da requisição
	 */
	protected String doGet(String url) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpGet doGet = new HttpGet(url);

			doGet.addHeader("Accept", "application/json");
			doGet.addHeader("X-CSRFToken", TOKEN);
			doGet.addHeader("Authorization", AUTH);
			
			String respostaJSON = executaRequisicaoERetornaJSON(httpClient, doGet);
			return respostaJSON;
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return null;
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
