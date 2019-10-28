package com.car.rental.bean;

import com.car.rental.service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * Custom implementation for @see {@link ModelMapper}. We're adding the
 * message resource for 18n internationalization @see {@link MessageService}
 */
public class CustomModelMapper extends ModelMapper {

	private MessageService messageService;
	 
	@Autowired
	public CustomModelMapper(MessageService messageService) {
		 this.messageService = messageService;
	 }

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	 
	 
}
