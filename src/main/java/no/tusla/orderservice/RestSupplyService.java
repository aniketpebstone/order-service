package no.tusla.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RestSupplyService 
{
	@Autowired
	RestTemplate restTemplate;
	
	public Mobile checkDeliveryForMobile(String company,String model) {
		log.info("~ In checkDeliveryForMobile ~");		
		final String uri = "http://localhost:8081/supply/"+company+"/"+model;
		//Temporary hardcode the response.
//		Mobile mobile=new Mobile();
//		mobile.setCompany(company);
//		mobile.setModel(model);
//		mobile.setOperatingSystem("Android 10");
//		mobile.setPrice(11200);
//		mobile.setRamInGb(2);
//		mobile.setBatteryCapacityInMilliAmp(3000);
		
		ResponseEntity<Mobile> mobileResp=restTemplate.getForEntity(uri, Mobile.class);
		log.info("Response from supply service:{}",mobileResp);
		return mobileResp.getBody();
	}
	
	public void placeSupplyOrder(MobileSupplyDto dto,User user) {
		log.info("~ In placeSupplyOrder ~");		
		log.info("MobileSupplyDto:{}",dto);
		Mobile mobile = checkDeliveryForMobile(dto.getCompany(), dto.getModel());
		log.info("Mobile:{}",mobile);
		if(mobile==null)
			throw new RuntimeException("Mobile is unavailable!");		
		int userPayingAmt=dto.getQuantity()*mobile.getPrice();
		log.info("userPayingAmt {}",userPayingAmt);
		if(userPayingAmt> user.getWalletBalance())
			throw new RuntimeException("Insufficient amount in wallet!");		
		log.info("user:{}",user);
		
	}

}
