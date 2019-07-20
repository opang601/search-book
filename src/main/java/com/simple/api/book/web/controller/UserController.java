package com.simple.api.book.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.web.controller.service.UserService;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	
	@Autowired
	private UserService userService;
	
	/**	회원가입
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/regist")
	public Result registUser(@RequestBody UsersEntity user) throws Exception {
		
		return userService.regist(user);
		 
	}
	@PostMapping("/idCheck")
	public Result idCheckUser(@RequestBody UsersEntity user) throws Exception {
		
		return userService.idCheckUser(user.getUserId());
		
	}
}
