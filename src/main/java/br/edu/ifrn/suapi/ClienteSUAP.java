package br.edu.ifrn.suapi;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import br.edu.ifrn.suapi.exception.CredenciaisIncorretasException;
import br.edu.ifrn.suapi.exception.FalhaAoConectarComSUAPException;
import br.edu.ifrn.suapi.exception.TokenInvalidoException;
import br.edu.ifrn.suapi.model.CredenciaisSUAP;
import br.edu.ifrn.suapi.model.CursoSUAP;
import br.edu.ifrn.suapi.model.TokenSUAP;
import br.edu.ifrn.suapi.model.UsuarioSUAP;
import lombok.Getter;

public final class ClienteSUAP {

	private static final String PREFIX = "https://suap.ifrn.edu.br/api/v2/";

	private static final String AUTENTICACAO_TOKEN_URL = PREFIX + "autenticacao/token/";

	private static final String MEUS_DADOS_URL = PREFIX + "minhas-informacoes/meus-dados/";

	private static final String VALIDA_TOKEN_URL = PREFIX + "autenticacao/token/verify/";

	@Getter
	private final String TOKEN;

	private final String AUTH;

	private final CloseableHttpClient httpClient;

	private final Gson gson = new Gson();

	public ClienteSUAP(String matricula, String senha)
			throws FalhaAoConectarComSUAPException, CredenciaisIncorretasException {

		httpClient = HttpClients.createDefault();

		try {
			TokenSUAP token = requestToken(matricula, senha);
			this.TOKEN = token.getToken();
			if (!this.isAutenticado())
				throw new CredenciaisIncorretasException();

			this.AUTH = generateAuthentication();
		} catch (IOException e) {
			throw new FalhaAoConectarComSUAPException(e);
		}
	}

	public ClienteSUAP(String token) throws TokenInvalidoException, FalhaAoConectarComSUAPException {
		httpClient = HttpClients.createDefault();

		try {
			boolean isTokenInvalido = !isTokenValido(token);
			if (isTokenInvalido) {
				throw new TokenInvalidoException();
			}
		} catch (IOException e) {
			throw new FalhaAoConectarComSUAPException(e);
		}

		this.TOKEN = token;
		this.AUTH = generateAuthentication();
	}

	public <T extends UsuarioSUAP> T getUsuario(Class<T> clazz) {
		if (!isAutenticado()) {
			return null;
		}

		String meusDados = doGet(MEUS_DADOS_URL);
		T usuario = gson.fromJson(meusDados, clazz);
		usuario.ajustaURL();
		usuario.defineClienteSUAP(this);
		return usuario;
	}

	public CursoSUAP getCurso(String codigo) {
		if (!this.isAutenticado()) {
			return null;
		}
		
		String url = PREFIX + "edu/cursos/" + codigo;
		String cursoJSON = doGet(url);
		CursoSUAP curso = gson.fromJson(cursoJSON, CursoSUAP.class);
		return curso;
	}

	protected String doGet(String url) {
		try {
			HttpGet doGet = new HttpGet(url);
			doGet.addHeader("Accept", "application/json");
			doGet.addHeader("X-CSRFToken", TOKEN);
			doGet.addHeader("Authorization", AUTH);

			return requestAndGetResponseJSON(doGet);
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		return null;
	}

	private String generateAuthentication() {
		return "JWT " + this.TOKEN;
	}

	public boolean isAutenticado() {
		return this.TOKEN != null;
	}

	private boolean isTokenValido(String token) throws IOException {
		if (token == null || token.isEmpty()) {
			return false;
		}

		HttpPost doPost = new HttpPost(VALIDA_TOKEN_URL);
		StringEntity body = new StringEntity(gson.toJson(new TokenSUAP(token)));
		doPost.setEntity(body);

		String json = requestAndGetResponseJSON(doPost);
		TokenSUAP tokenSUAP = gson.fromJson(json, TokenSUAP.class);

		if (tokenSUAP == null || tokenSUAP.getToken() == null || tokenSUAP.getToken().contains("Error")) {
			return false;
		}
		return true;
	}

	private TokenSUAP requestToken(String matricula, String senha)
			throws ClientProtocolException, IOException {
		HttpPost doPost = new HttpPost(AUTENTICACAO_TOKEN_URL);

		CredenciaisSUAP credenciais = new CredenciaisSUAP(matricula, senha);
		StringEntity body = new StringEntity(gson.toJson(credenciais));
		doPost.setEntity(body);

		String json = requestAndGetResponseJSON(doPost);
		TokenSUAP token = gson.fromJson(json, TokenSUAP.class);
		return token;
	}

	private String requestAndGetResponseJSON(HttpRequestBase doRequest)
			throws IOException, ClientProtocolException {
		String mediaType = "application/json";
		doRequest.setHeader("Content-Type", mediaType);;
		doRequest.setHeader("Accept", mediaType);;
		return httpClient.execute(doRequest, response -> EntityUtils.toString(response.getEntity()));
	}
}
