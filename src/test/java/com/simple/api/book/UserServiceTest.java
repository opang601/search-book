package com.simple.api.book;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.response.ResultCode;
import com.simple.api.book.web.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Test
	public void userRegistTest() {
		UsersEntity user = new UsersEntity();
		user.setUserId("testId");
		user.setUserName("테스트");
		user.setUserPwd("1234");
		
		userService.regist(user);
		logger.info(user.toString());
		assertThat(user.getUserId(), is("testId"));
		assertThat(user.getUserIdx(), is(notNullValue()));
		
	}
	
	@Test
	public void idCheckTest() {
		Result result = userService.idCheckUser("ch601");
		assertThat(result.getResultCode(), is(ResultCode.FAIL.getCode()));
		
	}

}
