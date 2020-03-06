package no.tusla.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	 UserRepo userRepo;
	
	public User authenticate(String userName,String password)
	{
		User user=userRepo.findOneUserByUserName(userName);
		if(user==null)
			throw new RuntimeException("Authentication failed!");
		return user;
	}
}
