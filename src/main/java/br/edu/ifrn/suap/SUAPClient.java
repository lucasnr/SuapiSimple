package br.edu.ifrn.suap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import br.edu.ifrn.suap.gson.GsonUtil;
import br.edu.ifrn.suap.model.ComponenteCurricularSUAP;
import br.edu.ifrn.suap.model.CursoSUAP;
import br.edu.ifrn.suap.model.UsuarioSUAP;

/**
 * Classe que representa um cliente do SUAP. Esta classe é responsável por fazer
 * as requisições ao servidor do SUAP e retornar as informações, vindas em JSON,
 * em objetos Java
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.0
 * 
 */
public class SUAPClient {

	public static void main(String[] args) {
		SUAPClient client = new SUAPClient("20161164010023", "ifrn.445566778899");
		System.out.println(client.getUsuario());
		System.out.println(client.getUsuario().getVinculo());

		System.out.println("\n\n\n");
		System.out.println(client.getUsuario().getCurso());

		for (ComponenteCurricularSUAP comp : client.getUsuario().getCurso().getComponentesCurriculares()) {
			System.out.println(comp);
		}
	}

	/**
	 * O TOKEN deste cliente
	 * 
	 * @since 1.0
	 */
	private String TOKEN;

	/**
	 * A string de autenticação deste cliente, usada para realizar requisições ao
	 * SUAP
	 * 
	 * @since 1.0
	 */
	private String AUTH;

	/**
	 * Construtor único, que define um TOKEN e uma Autenticação realizando uma
	 * requisição POST ao servidor do SUAP com as credenciais recebidas
	 * 
	 * @param matricula A matricula do usuário
	 * @param senha     A senha do SUAP deste usuário
	 * @since 1.0
	 */
	public SUAPClient(String matricula, String senha) {

		// cria um objeto de HttpClient, responsavel por executar requisições.
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			// URL da requisição
			String url = "https://suap.ifrn.edu.br/api/v2/autenticacao/token/";

			// cria um objeto de HttPost que representa uma requisição do tipo POST à URL
			// que recebe no construtor
			HttpPost doPost = new HttpPost(url);

			// lista de par de valores, parâmetros que serão passados na requisição POST
			List<NameValuePair> parametros = new ArrayList<NameValuePair>(2);
			parametros.add(new BasicNameValuePair("username", matricula));
			parametros.add(new BasicNameValuePair("password", senha));

			// atribui a lista de parâmetros à requisição
			doPost.setEntity(new UrlEncodedFormEntity(parametros, "UTF-8"));

			// cria um cuidador de resposta
			ResponseHandler<String> responseHandler = new SUAPTokenJSONHandler();
			String token = httpClient.execute(doPost, responseHandler);

			// atribui o token ao atributo da classe
			this.TOKEN = token;

			// a autenticação é 'JWT + token'
			this.AUTH = "JWT " + this.TOKEN;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// fecha o cliente HTTP
				httpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retorna um objeto java do usuário deste cliente, com os dados da requisição
	 * GET 'meus-dados' do SUAP
	 * 
	 * @since 1.0
	 */
	public UsuarioSUAP getUsuario() {
		if (!isAutenticado()) {
			return null;
		}

		// Obtem as informações básicas do usuário logado no SUAP
		String url = "https://suap.ifrn.edu.br/api/v2/minhas-informacoes/meus-dados/";
		String meusDados = doGet(url);
		Gson gson = GsonUtil.getGson();
		UsuarioSUAP usuario = gson.fromJson(meusDados, UsuarioSUAP.class);
		usuario.ajustaURL();
		usuario.setSuapClient(this);
		return usuario;
	}

	/**
	 * Faz uma requisição GET 'cursos' ao SUAP e retorna um objeto java de Curso
	 * 
	 * @param codigo O codigo do curso no SUAP
	 */
	public final CursoSUAP getCurso(String codigo) {
		String url = "https://suap.ifrn.edu.br/api/v2/edu/cursos/" + codigo + "/";
		String cursoJSON = doGet(url);
		Gson gson = GsonUtil.getGson();
		CursoSUAP curso = gson.fromJson(cursoJSON, CursoSUAP.class);
		return curso;
	}

	/**
	 * Faz uma requisição do tipo GET à alguma URL, e retorna um JSON vindo da
	 * resposta da requisição
	 * 
	 * @since 1.0
	 * @param url URL à qual se deseja fazer uma requisição GET
	 */
	protected String doGet(String url) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpget = new HttpGet(url);

			httpget.addHeader("Accept", "application/json");
			httpget.addHeader("X-CSRFToken", TOKEN);
			httpget.addHeader("Authorization", AUTH);

			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					HttpEntity entity = response.getEntity();
					return entity != null ? EntityUtils.toString(entity) : null;
				}

			};

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

		return "";
	}

	/**
	 * Verifica se o cliente está autenticado ou não, ou seja, se na construção do
	 * objeto, as credenciais foram informadas corretamente.
	 * 
	 * @since 1.0
	 */
	public boolean isAutenticado() {
		return this.TOKEN != null;
	}

}
