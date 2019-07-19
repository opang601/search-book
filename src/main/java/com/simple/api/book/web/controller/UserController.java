package com.simple.api.book.web.controller;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/list")
	public UsersEntity getUserList() throws Exception {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		UsersEntity user = new UsersEntity();
		user.setUserId("choh");
		user.setUserName("오충환");
		user.setUserPwd("12345");
		user.setRegDt(currentDateTime);
		
		userRepository.save(user);
		logger.info("messge : {}", userRepository.count());
		logger.info("messge : {}", userRepository.findAll().get(0));
		 
		return user;
		
	}
}
