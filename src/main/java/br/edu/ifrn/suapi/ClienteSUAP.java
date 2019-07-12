package br.edu.ifrn.suapi;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import br.edu.ifrn.suapi.exception.CredenciaisIncorretasException;
import br.edu.ifrn.suapi.exception.FalhaAoConectarComSUAPException;
import br.edu.ifrn.suapi.exception.TokenInvalidoException;
import br.edu.ifrn.suapi.model.CredenciaisSUAP;
import br.edu.ifrn.suapi.model.CursoSUAP;
import br.edu.ifrn.suapi.model.TokenSUAP;
import br.edu.ifrn.suapi.model.UsuarioSUAP;
import lombok.Getter;

public final class ClienteSUAP {

	private static final String AUTENTICACAO_TOKEN_PATH = "autenticacao/token/";

	private static final String MEUS_DADOS_PATH = "minhas-informacoes/meus-dados/";

	private static final String VALIDA_TOKEN_PATH = "autenticacao/token/verify/";

	private static final String CURSO_PATH = "edu/cursos/{codigo}";
	
	@Getter
	private final String TOKEN;

	private final String AUTH;

	private final SUAPRestClient client = new SUAPRestClient();

	public ClienteSUAP(String matricula, String senha)
			throws FalhaAoConectarComSUAPException, CredenciaisIncorretasException {

		TokenSUAP token = requestToken(matricula, senha);

		this.TOKEN = token.getToken();
		this.AUTH = generateAuthentication();
		
		addHeaders();
	}

	public ClienteSUAP(String token) throws TokenInvalidoException, FalhaAoConectarComSUAPException {
		boolean isTokenInvalido = !isTokenValido(token);
		if (isTokenInvalido) {
			throw new TokenInvalidoException();
		}

		this.TOKEN = token;
		this.AUTH = generateAuthentication();

		addHeaders();
	}

	private void addHeaders() {
		client.addHeader("X-CSRFToken", TOKEN);
		client.addHeader("Authorization", AUTH);
	}

	public <T extends UsuarioSUAP> T getUsuario(Class<T> clazz) {
		if (!isAutenticado()) {
			return null;
		}

		T usuario = client.doGet(MEUS_DADOS_PATH, clazz);
		usuario.ajustaURL();
		usuario.defineClienteSUAP(this);
		return usuario;
	}

	public CursoSUAP getCurso(String codigo) {
		if (!this.isAutenticado()) {
			return null;
		}
		
		String uri = CURSO_PATH.replace("{codigo}", codigo);
		CursoSUAP curso = client.doGet(uri, CursoSUAP.class);
		return curso;
	}

	private String generateAuthentication() {
		return "JWT " + this.TOKEN;
	}

	public boolean isAutenticado() {
		return this.TOKEN != null;
	}

	private boolean isTokenValido(String token) throws FalhaAoConectarComSUAPException {
		if (token == null || token.isEmpty()) {
			return false;
		}

		TokenSUAP tokenObject = new TokenSUAP(token);
		try {
			client.doPost(VALIDA_TOKEN_PATH, tokenObject, TokenSUAP.class);
		} catch (Exception e) {
			if (e.getCause() instanceof UnrecognizedPropertyException)
				return false;
			throw new FalhaAoConectarComSUAPException(e);
		}

		return true;
	}

	private TokenSUAP requestToken(String matricula, String senha) 
			throws CredenciaisIncorretasException, FalhaAoConectarComSUAPException {
		CredenciaisSUAP credenciais = new CredenciaisSUAP(matricula, senha);
		try {
			TokenSUAP token = client.doPost(AUTENTICACAO_TOKEN_PATH, credenciais, TokenSUAP.class);
			return token;
		} catch (Exception e) {
			if(e.getCause() instanceof UnrecognizedPropertyException)
				throw new CredenciaisIncorretasException();

			throw new FalhaAoConectarComSUAPException(e);
		}
	}

}
