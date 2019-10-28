package com.car.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class RestService {
	
	@Autowired
	private RestTemplate restTemplate;
	
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

	
	public <T> ResponseEntity<T> doRequest(URI uri, HttpMethod verb, HttpEntity<?> body, ParameterizedTypeReference<T> responseType) {
		return doInternalRequest(uri, verb, body, responseType, true);
	}

	public <T> ResponseEntity<T> doRequest(String uri, HttpMethod verb, HttpEntity<?> body, ParameterizedTypeReference<T> responseType) {
		return doInternalRequest(uri, verb, body, responseType, true);
	}
	
	private <T> ResponseEntity<T> doInternalRequest(Object uri, HttpMethod verb, HttpEntity<?> body, ParameterizedTypeReference<T> responseType, boolean loadBalance) {
		try {
			if ( uri instanceof String ) {
				return restTemplate.exchange((String)uri, verb, body, responseType);
			}
			if ( uri instanceof URI ) {
				return restTemplate.exchange((URI) uri, verb, body, responseType);
			}
		} catch (HttpStatusCodeException e) {
			System.out.println(e);
		}
		return null;
	}

}
