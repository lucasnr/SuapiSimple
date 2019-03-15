package br.edu.ifrn.suap.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

	public static Gson getGson() {
		Gson gson = new GsonBuilder().setExclusionStrategies(new GsonIgnoreStrategy()).create();
		return gson;
	}
}
