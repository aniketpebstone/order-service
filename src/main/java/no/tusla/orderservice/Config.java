package no.tusla.orderservice;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

		
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder)
	{
		 RestTemplate restTemplate = restTemplateBuilder.errorHandler(getSupplyResponseErrorHandler()).build();
		 return restTemplate;
	}
	
	@Bean
	public SupplyResponseErrorHandler getSupplyResponseErrorHandler()
	{
		return new SupplyResponseErrorHandler();
	}
}
