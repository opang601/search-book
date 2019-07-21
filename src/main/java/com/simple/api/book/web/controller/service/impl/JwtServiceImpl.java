package com.simple.api.book.web.controller.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.simple.api.book.config.exception.UnauthorizedException;
import com.simple.api.book.web.controller.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService{
	private static final String SALT =  "BookSearchSaltSecret";
	
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	// 발급 시 Token 만료 : 1시간, Refresh 주기 : 30분. 
	// Token 만료 시간 설정
	private int MINUTES = 60 * 1;

	
	/**	
	 * 토큰 생성
	 * @param <T>
	 * @param key
	 * @param data
	 * @param subject
	 * @return
	 */
	@Override
	public <T> String create(String key, T data, String subject){
		//만료시간
		Date expireTime = new Date();
	    expireTime.setTime(expireTime.getTime() + 1000 * 60 * MINUTES);
		
		String jwt = Jwts.builder()
						 .setHeaderParam("typ", "JWT")
						 .setHeaderParam("regDate", System.currentTimeMillis())
						 .setSubject(subject)
						 .setExpiration(expireTime)
						 .claim(key, data)
						 .signWith(SignatureAlgorithm.HS256, this.generateKey())
						 .compact();
		return jwt;
	}
	
	/**	
	 * 토큰 검증
	 * @param jwt
	 * @return
	 */
	@Override
	public boolean isUsable(String jwt) {
		try{
			Jws<Claims> claims = Jwts.parser()
						  .setSigningKey(this.generateKey())
						  .parseClaimsJws(jwt);
			return true;
			
		}catch (Exception e) {
			throw new UnauthorizedException();
			 
		}
	}
	
	/** 
	 * 키 생성
	 * @return
	 */
	private byte[] generateKey(){
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnauthorizedException("Making JWT Key Error" + e.getMessage());
		}
		
		return key;
	}

	/**	
	 * 토큰으로 부터 정보 조회
	 * @param key
	 * @return
	 */
	public Map<String, Object> get(String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader("Authorization");
		Jws<Claims> claims = null;
		
		try {
			claims = Jwts.parser()
						 .setSigningKey(SALT.getBytes("UTF-8"))
						 .parseClaimsJws(jwt);
		} catch (Exception e) {
		throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (LinkedHashMap<String, Object>)claims.getBody().get(key);
		
		
		return value;
	}
	
	@Override
	public String getUserName() {
		return (String)this.get("member").get("userName");
	}
	@Override
	public String getUserId() {
		return (String)this.get("member").get("userId");
	}
	
	
	
}
