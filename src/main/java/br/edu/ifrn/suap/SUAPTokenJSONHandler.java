package br.edu.ifrn.suap;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

public class SUAPTokenJSONHandler implements ResponseHandler<String> {

	public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
		HttpEntity entity = response.getEntity();
		String jsonString = EntityUtils.toString(entity);
		
		Gson gson = new Gson();
		Map<?, ?> map = gson.fromJson(jsonString, Map.class);
		Object token = map.get("token");
		if (token != null) {
			return token.toString();
		}
		
		return null;
	}

}
