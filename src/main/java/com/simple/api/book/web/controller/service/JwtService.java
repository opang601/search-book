package com.simple.api.book.web.controller.service;

public interface JwtService {

	public <T> String create(String key, T data, String subject);

	public boolean isUsable(String jwt);
	
	public String getUserName();

	public String getUserId();
	
}
