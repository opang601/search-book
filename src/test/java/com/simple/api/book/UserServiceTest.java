package com.simple.api.book;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.repository.UserRepository;
import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.response.ResultCode;
import com.simple.api.book.web.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * <pre>
	 *	회원 저장 테스트
	 * </pre>
	 */
	@Test
	public void userRegistTest() {
		UsersEntity user = new UsersEntity();
		String userId = "testId";
		
		user.setUserId(userId);
		user.setUserName("테스트");
		user.setUserPwd("1234");
		
		userService.regist(user);
		logger.info(user.toString());
		
		List<UsersEntity> userList = userRepository.findByUserId(userId);
		
		assertThat(userList.get(0).getUserId(), is(userId));
		
	}
	
	/**
	 * <pre>
	 *	ID중복 여부 테스트(ID중복이라면 fail return)
	 * </pre>
	 */
	@Test
	public void idCheckTest() {
		Result result = userService.idCheckUser("ch601");
		assertThat(result.getResultCode(), is(ResultCode.FAIL.getCode()));
		
	}

}
