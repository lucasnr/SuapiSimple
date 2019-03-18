package br.edu.ifrn.suap;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

/**
 * Classe responsável por receber de uma resposta um JSON e retornar-lo como uma
 * String
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.0
 * @version 1.1
 */
public class JSONHandler implements ResponseHandler<String> {

	public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		HttpEntity entity = response.getEntity();
		String jsonString = EntityUtils.toString(entity);
		return jsonString;
	}

}
