package com.car.rental.service;

import com.car.rental.bean.CustomModelMapper;
import com.car.rental.util.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.io.IOException;

@Service
public class AbstractMessage {

	//**********************************************************************************
	// PUBLIC ATTRIBUTES
	//**********************************************************************************
	
	/** Default logger */
    public final Logger log = LoggerFactory.getLogger(AbstractMessage.class);
    
    @Autowired
 	public MessageService messageService;
    @Autowired
    public CustomModelMapper mapper;
     
     
    //**********************************************************************************
 	// PUBLIC FUNCTIONS
 	//**********************************************************************************
     
     /**
 	 * <p> Throws the exception based on the parameters informed. Here we'll create 
 	 * the exception according with the responseStatus and set the message based 
 	 * on the current locale. 
 	 * 
 	 * @param key The message 18n key
 	 * @param args The message arguments
 	 * @throws IOException 
 	 */
 	public void throwsException(String key, String... args) throws IOException {
 	
 		RestClientException ex = new RestClientException(buildMessage(key,args));
		
		throw ex;
 	}
 	
 	/**
 	 * <p> Throws the exception for a specific type, first we need to remove the
 	 * message from the origin exception and create a new one to be thrown.
 	 * 
 	 * @param e @see {@link HttpStatusCodeException}
 	 * @throws RestClientException 
 	 */
 	public void throwsException(HttpStatusCodeException e) {
 		throw new RestClientException(JsonParser.doErrorParser(e).getMessage());
 	}
 	
 	/**
 	 * <p> Throws the exception based on the message informed. 
 	 * @param message The message 18n key
 	 * @param args The message arguments
 	 * @throws IOException 
 	 */
 	public void throwsException(String message) throws IOException {
		throw new RestClientException(buildMessage(message,""));
 	}
 	
 	public void throwsExceptionWithoutBuildMessage(String message) throws IOException {
		throw new RestClientException(message);
 	}
 	
 	/**
 	 * <p> Throws the exception based on the message informed. 
 	 * @param message The message arguments
 	 * @throws IOException 
 	 */
 	public static void throwsStaticException(String message) throws IOException {
 		AbstractMessage msg = new AbstractMessage();
 		throw new RestClientException(msg.buildMessage(message,""));
 	}
 	
 	public void throwsRuntimeException(String key, String... args) throws RuntimeException {
		throw new RuntimeException(buildMessage(key,args));
 	}
 	
 	//**********************************************************************************
	// PRIVATE FUNCTIONS
	//**********************************************************************************

 	
 	/**
	 * <p> Gets the error message based on the key, arguments and locale.
	 * 
	 * @param key The error message key
	 * @param args The message arguments.
	 * @return The complete error message. 
	 */
	public String buildMessage(String key, String... args) {
		String errorMessage = messageService.getMessage(key, args);
		log.debug(errorMessage);
		return errorMessage;
	}
	
	
}
