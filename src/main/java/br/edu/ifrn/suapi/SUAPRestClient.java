package br.edu.ifrn.suapi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public final class SUAPRestClient {

	private static final String MEDIA_TYPE = MediaType.APPLICATION_JSON;

	private static final String SUAP_URI = "https://suap.ifrn.edu.br/api/v2/";

	private final Client client = ClientBuilder.newClient();

	private MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
	
	public SUAPRestClient() {
		this.addHeader("Content-Type", MEDIA_TYPE);
		this.addHeader("Accept", MEDIA_TYPE);
	}	
	
	public Response doGet(String path) {
		return request(path).get();
	}

	public <T> T doGet(String path, Class<T> responseType) {
		return doGet(path).readEntity(responseType);
	}
	
	public <E> Response doPost(String path, E entity) {
		return request(path)
				.post(createEntity(entity));
	}

	public <T, E> T doPost(String path, E entity, Class<T> responseType) {
		return doPost(path, entity).readEntity(responseType);
	}
	
	public <E> Response doPut(String path, E entity) {
		return request(path).put(createEntity(entity));
	}
	
	public <T, E> T doPut(String path, E entity, Class<T> responseType) {
		return doPut(path, entity).readEntity(responseType);
	}
	
	public <E> Response doPatch(String path, E entity) {
		return request(path).build("PATCH", createEntity(entity)).invoke();
	}
	
	public <T, E> T doPatch(String path, E entity, Class<T> responseType) {
		return doPatch(path, entity).readEntity(responseType);
	}
	
	public Response doDelete(String path) {
		return request(path).delete();
	}
	
	private Builder request(String path) {
		return client
				.target(SUAP_URI)
				.path(path)
				.request()
				.headers(headers);
	}
	
	private <E> Entity<E> createEntity(E entity) {
		return Entity.entity(entity, MEDIA_TYPE);
	}

	public void addHeader(String name, String value) {
		this.headers.putSingle(name, value);
	}
}
