package br.edu.ifrn.suap.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonIgnoreStrategy implements ExclusionStrategy{

	public boolean shouldSkipField(FieldAttributes f) {
		return f.getAnnotation(GsonIgnore.class) != null;
	}

	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
