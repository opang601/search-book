package com.simple.api.book.web.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.repository.UserRepository;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/api/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/regist")
	public List<UsersEntity> registUser(@RequestBody UsersEntity user) throws Exception {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		user.setRegDt(currentDateTime);
		
		userRepository.save(user);
		logger.info("messge : {}", userRepository.count());
		logger.info("messge : {}", userRepository.findAll().get(0));
		 
		return userRepository.findAll();
		
	}
}
