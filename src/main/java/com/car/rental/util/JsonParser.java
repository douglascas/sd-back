package com.car.rental.util;

import com.car.rental.dto.MessageDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * <p> Only to encapsulate the default Json parser.
 */
public class JsonParser {

	/**
	 * Default parser 
	 */
	private static final Gson gson = new GsonBuilder().create();

	/**
	   * This method deserializes the specified Json into an object of the specified class. It is not
	   * suitable to use if the specified class is a generic type since it will not have the generic
	   * type information because of the Type Erasure feature of Java. Therefore, this method should not
	   * be used if the desired type is a generic type. Note that this method works fine if the any of
	   * the fields of the specified object are generics, just the object itself should not be a
	   * generic type. For the cases when the object is of generic type, invoke
	   * {@link #fromJson(String, Type)}. If you have the Json in a {@link Reader} instead of
	   * a String, use {@link #fromJson(Reader, Class)} instead.
	   *
	   * @param <T> the type of the desired object
	   * @param json the string from which the object is to be deserialized
	   * @param entity the class of T
	   * @return an object of type T from the string. Returns {@code null} if {@code json} is {@code null}.
	   * @throws JsonSyntaxException if json is not a valid representation for an object of type
	   * classOfT
	   */
	public static <T> T fromJsonToEntity(String json, Class<T> entity) {
		return gson.fromJson(json, entity);
	}

    /**
     * Converts any object to its JSON string representation.
     *
     * @param obj Any Object to be converted.
     * @return The JSON string.
     */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
	
	/**
	 * <p> Removes the message from the informed exception.
	 * @param e @see {@link HttpStatusCodeException}
	 * @return @see {@link MessageDTO}
	 */
	public static MessageDTO doErrorParser(HttpStatusCodeException e) {
		return gson.fromJson(e.getResponseBodyAsString(), MessageDTO.class);
	}

	public static Gson getGson() {
		return gson;
	}
}
