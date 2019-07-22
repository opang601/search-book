package com.simple.api.book.web.service;

import com.simple.api.book.common.domain.entity.UsersEntity;

public interface JwtService {

	public <T> String create(String key, T data, String subject);

	public boolean isUsable(String jwt);
	
	public String getUserName();

	public String getUserId();

	public UsersEntity getUser();
	
}
