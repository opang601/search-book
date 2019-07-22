package com.simple.api.book.web.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.repository.UserRepository;
import com.simple.api.book.common.domain.vo.user.LoginVO;
import com.simple.api.book.common.util.SHA256Util;
import com.simple.api.book.config.exception.UnauthorizedException;
import com.simple.api.book.web.service.AuthService;
import com.simple.api.book.web.service.JwtService;

@Service
public class AuthServiceImpl implements AuthService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public LoginVO login(String userId, String password) {
		
		List<UsersEntity> findUserList = userRepository.findByUserId(userId);
		
		if(findUserList == null || findUserList.isEmpty()) {
			throw new UnauthorizedException("아이디가 존재하지 않습니다.");
		}
		UsersEntity targetUser = findUserList.get(0);
		
		//비밀번호 암호화
		String encPwd = SHA256Util.getEncrypt(password, targetUser.getSalt());
		
		logger.info("###### Pass Encrypt encPwd : {}, dbPwd : {}", encPwd, targetUser.getUserPwd());
		
		if(!targetUser.getUserPwd().equals(encPwd)) {
			throw new UnauthorizedException("비밀번호를 정확하게 입력해주세요.");
		}
		
		LoginVO loginVO = setLoginInfo(targetUser);
		return loginVO;
	}
	
	@Override
	public LoginVO refresh() {
		//추후 IAM에도 계정유무 파악해서 적용해야함.
		List<UsersEntity> findUserList = userRepository.findByUserId(jwtService.getUserId());
		if(findUserList == null || findUserList.isEmpty()) {
			throw new UnauthorizedException("아이디가 존재하지 않습니다.");
		}
		
		UsersEntity targetUser = findUserList.get(0);
		LoginVO loginVO = setLoginInfo(targetUser);
		
		return loginVO;
	}
	
	public LoginVO setLoginInfo(UsersEntity targetUser) {
		
		LoginVO loginVO = new LoginVO();
		loginVO.setUserId(targetUser.getUserId());
		loginVO.setUserName(targetUser.getUserName());
		
		return loginVO;
	}
}






