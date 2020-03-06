package no.tusla.orderservice;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@Component
public class MobileSupplyDto {
	
	@NotNull(message = "company must not be null")
	@JsonProperty(value = "company")
	private String company;
	
	@NotNull(message = "model must not be null")
	@JsonProperty(value = "model")
	private String model;
	
	@Min(value = 1, message = "quantity must be minimum 1")	
	@JsonProperty(value = "quantity")
	private int quantity;
}
