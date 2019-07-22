package com.simple.api.book.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.web.service.UserService;

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
	public Result registUser(@RequestBody UsersEntity user) {
		
		return userService.regist(user);
		 
	}
	
	/**	아이디 중복체크
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/idDuplCheck")
	public Result idDuplCheck(@RequestBody UsersEntity user)  {
		
		return userService.idCheckUser(user.getUserId());
		
	}
	
	/**	회원조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("")
	public Result getUserList() {
			
		return userService.getUserList();
		 
	}
}
