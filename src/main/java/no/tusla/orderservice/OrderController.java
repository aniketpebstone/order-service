package no.tusla.orderservice;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "order")
public class OrderController {
 
  @Autowired
  RestSupplyService supplyService;
  
  @Autowired
  UserService userService;
  
  
	@GetMapping(value = "/{company}/{model}")
	public  ResponseEntity<?> checkAvailableMobiles(
			@PathVariable("company") String company,
			@PathVariable("model") String model){
		log.info("~ In checkAvailableMobiles ~");
		log.info("company:{} model:{}",company,model);
		Mobile mobile = supplyService.checkDeliveryForMobile(company, model);
		log.info("mobile:{}",mobile);
		if(mobile==null)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(mobile);
	}
	
	@PostMapping()
	public ResponseEntity<?> placeSupplyOrderForMobile(
			@RequestHeader(value = "access-key", required = true) String userName,
			@RequestHeader(value = "access-token", required = true) String token,
			@Valid @RequestBody MobileSupplyDto dto){
			log.info("~ In placeSupplyOrderForMobile ~");
			User user=userService.authenticate(userName,token);
			supplyService.placeSupplyOrder(dto,user);
			return ResponseEntity.accepted().build();
		
	}
	
}
