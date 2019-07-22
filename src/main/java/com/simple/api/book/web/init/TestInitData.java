package com.simple.api.book.web.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.web.service.UserService;

@Component
public class TestInitData implements ApplicationRunner {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;


    public void run(ApplicationArguments args) {
    	//초기 회원 가입 테스트 
    	UsersEntity user = new UsersEntity();
    	user.setUserId("ch601");
    	user.setUserName("오충환");
    	user.setUserPwd("1234");
    	userService.regist(user);
    	logger.info("User Info [{}] {}",user.getUserIdx(),user.getUserId());
    }
}