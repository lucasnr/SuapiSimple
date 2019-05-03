package br.edu.ifrn.suap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import br.edu.ifrn.suap.model.CursoSUAP;
import br.edu.ifrn.suap.model.UsuarioSUAP;
import br.edu.ifrn.suap.util.JSONHandler;

/**
 * Classe que representa um cliente do SUAP. Esta classe é responsável por fazer
 * as requisições ao servidor do SUAP e retornar as informações, vindas em JSON,
 * em objetos Java
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.1
 */
public class ClienteSUAP {

	/**
	 * O TOKEN deste cliente
	 * 
	 * @since 1.0
	 */
	private String TOKEN;

	/**
	 * A autenticação deste cliente, usada para realizar requisições ao SUAP
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
	public ClienteSUAP(String matricula, String senha) {

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
			ResponseHandler<String> responseHandler = new JSONHandler();
			String jsonString = httpClient.execute(doPost, responseHandler);

			// pega o atributo 'token' do JSON
			Gson gson = new Gson();
			Map<?, ?> map = gson.fromJson(jsonString, Map.class);
			String token = null;
			if (map.get("token") != null) {
				token = map.get("token").toString();
			}

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
	 * Retorna um objeto java parametizado do cliente, sendo o seu tipo o que for
	 * enviado como parâmetro da chamada (Aluno, Servidor)
	 *
	 * @param clazz Classe que herde de UsuarioSUAP
	 * @return AlunoSUAP ou ServidorSUAP
	 * @since 1.1
	 */
	@SuppressWarnings("unchecked")
	public <T extends UsuarioSUAP> T getUsuario(Class<T> clazz) {
		if (!isAutenticado()) {
			return null;
		}

		// Obtem as informações básicas do usuário logado no SUAP
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
	 * @param codigo O codigo do curso no SUAP
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
	 * @param url URL à qual se deseja fazer uma requisição GET
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

}
