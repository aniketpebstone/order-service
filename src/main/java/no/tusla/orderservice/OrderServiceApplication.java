package no.tusla.orderservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class OrderServiceApplication implements CommandLineRunner{
	
	@Autowired
	UserRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<?, ?>> users = objectMapper.readValue(new ClassPathResource("users.json").getFile(), List.class);
        List<User> userList=new ArrayList<>();
        for(Map<?, ?> map:users) {
        	User user =new User();        	
        		user.setUserName(map.get("user_name").toString());
        		user.setPassword(map.get("password").toString());
        		user.setWalletBalance(new Integer(map.get("wallet_balance").toString()));        		
        		userList.add(user);
        	}                
        log.info("Initializing user database... {}",userList);
        repo.saveAll(userList);        
        }
}

