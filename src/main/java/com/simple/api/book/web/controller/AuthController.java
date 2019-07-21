package com.simple.api.book.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.vo.user.LoginVO;
import com.simple.api.book.web.controller.service.AuthService;
import com.simple.api.book.web.controller.service.JwtService;

@CrossOrigin( "*" )
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	@Autowired
    private AuthService authService;
 
    @Autowired
    private JwtService jwtService;
     
    /**	로그인 처리
     * @param userId
     * @param password
     * @return
     */
    @PostMapping(value="/login")
    public Result login(@RequestParam(required=true) String userId, 
    					@RequestParam(required=true) String password) {
    	Result result = Result.successInstance();
    	
    	LoginVO loginMember = authService.login(userId, password);

        String token = jwtService.create("member", loginMember, "user");
        loginMember.setToken(token);
        
        result.setData(loginMember);
        return result;
    }
    
    
    /**	Token 갱신 처리
     * @return
     */
    @PostMapping(value="/refresh")
    public Result refresh(){
    	Result result = Result.successInstance();
    	LoginVO loginMember = authService.refresh();
    	String token = jwtService.create("member", loginMember, "user");
    	loginMember.setToken(token);
    	result.setData(loginMember);
    	return result;
    }
}
