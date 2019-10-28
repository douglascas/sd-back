package com.car.rental.config;

import com.car.rental.bean.CustomModelMapper;
import com.car.rental.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * Model mapper configuration, based on 
 * @link http://modelmapper.org/getting-started/
 * 
 * @documentation
 * http://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application
 */
@Configuration
public class ModelMapperConfig {

	@Autowired
	private MessageService message;
	
	@Bean
	public CustomModelMapper modelMapper() {
	    return new CustomModelMapper(message);
	}
	
}
