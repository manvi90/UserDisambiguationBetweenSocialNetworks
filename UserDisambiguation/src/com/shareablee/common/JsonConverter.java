package com.shareablee.common;
import com.google.gson.Gson;


public class JsonConverter<T> {

	/**
	 * Convert an object to Json String
	 * @param obj
	 * @return
	 */
	public static<T> String getJsonString(T obj){
		Gson gson = new Gson();
		return (gson.toJson(obj));
	}


	/**
	 * Get a class object from a Json String
	 * @param jsonString
	 * @param objectClass
	 * @return 
	 */
	public static<T> T getObjectFromJson(final String jsonString, final Class<T> objectClass) {  
		Gson gson = new Gson();  
		return gson.fromJson(jsonString, objectClass);
	}
}