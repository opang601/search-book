package com.simple.api.book.web.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.repository.UserRepository;
import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.util.SHA256Util;
import com.simple.api.book.web.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Result regist(UsersEntity user) {
		Result result = Result.successInstance();
		
		try {
			List<UsersEntity> findUserList = userRepository.findByUserId(user.getUserId());
			
			if(findUserList != null && !findUserList.isEmpty()) {
				result = Result.failInstance();
				result.setMessage("사용중인 ID가 존재합니다.");
				return result;
			}
			//비밀번호 암호화
			String salt = SHA256Util.generateSalt();
			String pwd = user.getUserPwd();
			pwd = SHA256Util.getEncrypt(pwd, salt);
			
			user.setSalt(salt);
			user.setUserPwd(pwd);
			logger.info("###### Pass Encrypt pwd : {}, salt : {}", pwd, salt);
			
			user.setRegDt(new Date());
			UsersEntity saveUser = userRepository.save(user);
			
			if(saveUser.getUserIdx() == null ) {
				result = Result.failInstance();
				result.setMessage("사용자 등록이 실패하였습니다.");
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		
	}
	
	@Override
	public Result idCheckUser(String userId) {
		Result result = Result.successInstance();
		
		try {
			List<UsersEntity> findUserList = userRepository.findByUserId(userId);
			
			if(findUserList != null && !findUserList.isEmpty()) {
				result = Result.failInstance();
				result.setMessage("사용중인 ID가 존재합니다.");
			}
				
			} catch (Exception e) {
					
			}
			return result;
	}
	
	@Override
	public Result getUserList() {
		Result result = Result.successInstance();
		
		try {
			List<UsersEntity> findUserList = userRepository.findAll();
			result.setData(findUserList);
				
			} catch (Exception e) {
					
			}
			return result;
	}
	
}






