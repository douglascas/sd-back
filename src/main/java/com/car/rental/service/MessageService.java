package com.car.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Locale;


/**
 * Service for resolving messages, with support for the parameterization
 * and internationalization of such messages.
 *
 * <p>Spring provides two out-of-the-box implementations for production:
 * <ul>
 * <li>{@link org.springframework.context.support.ResourceBundleMessageSource},
 * built on top of the standard {@link java.util.ResourceBundle}
 * <li>{@link org.springframework.context.support.ReloadableResourceBundleMessageSource},
 * being able to reload message definitions without restarting the VM
 * </ul>
 * Currently, we're using the @see ReloadableResourceBundleMessageSource, the is configured
 * into the application context through 
 */
@Service
public class MessageService {

	@Autowired
	private MessageSource messageSource;
	public static final String FAILURE = "failure";
	
	/**
	 * <p> Try to resolve the message based on the key and arguments. Treat as an error if the 
	 * message can't be found.
	 * 
	 * @param key The key code to lookup up
	 * @param args Array of arguments that will be filled in for params within
	 * the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
	 * or {@code null} if none.
	 * @return The complete message with internationalization.
	 * @throws NoSuchMessageException if the message wasn't found 
	 */
	public String getMessage(String key, String... args) throws NoSuchMessageException {
		Locale locale = LocaleContextHolder.getLocale();
		String errorMessage = messageSource.getMessage(key, args, locale);
		return errorMessage;
	}
	
	/**
	 * <p> Try to resolve the message based on the key and arguments. Treat as an error if the 
	 * message can't be found.
	 * 
	 * @param key The key code to lookup up
	 * @return The complete message with internationalization.
	 * @throws NoSuchMessageException if the message wasn't found 
	 */
	public String getMessage(String key) throws NoSuchMessageException {
		return getMessage(key, "");
	}
	
	/**
	 * <p> Creates an HTTP Header and put the error message into
	 * @param message The error message to be shown
	 * @return An instance of HttpHeaders with the error message  
	 */
	public HttpHeaders createHttpHeaderErrorMessage(String message) {
		HttpHeaders header = new HttpHeaders();
		header.add(getMessage(FAILURE), message);
		return header;
	}
	
	/**
	 * <p> Creates an HTTP Header and put the error message into
	 * @param key The key code to lookup up
	 * @param args Array of arguments that will be filled in for params within
	 * the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
	 * or {@code null} if none.
	 * @return An instance of HttpHeaders with the error message  
	 */
	public HttpHeaders createHttpHeaderErrorMessage(String key, String... args) {
		return createHttpHeaderErrorMessage(getMessage(key, args));
	}
}
