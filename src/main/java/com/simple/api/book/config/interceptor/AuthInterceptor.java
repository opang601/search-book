package com.simple.api.book.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.response.ResultCode;
import com.simple.api.book.web.controller.service.JwtService;

@Component
public class AuthInterceptor implements HandlerInterceptor{
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	private static final String HEADER_AUTH = "Authorization";

	@Autowired
	private JwtService jwtService;
	
	/**
	 * 인증(Authentication) 체크 Interceptor
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		final String token = request.getHeader( HEADER_AUTH );
		logger.info( "###### Access Token Info : {}", token );
		logger.info( "###### Access User Info : [{}]:{}", jwtService.getUserId(), jwtService.getUserName() );
		
		if ( token != null && jwtService.isUsable( token ) ) {
			return true;
		} else {
			Result result = Result.failInstance();
			result.setStatusCode( HttpStatus.UNAUTHORIZED );
			result.setMessage( ResultCode.UNAUTHRIZED.getDesc());

			ObjectMapper mapper = new ObjectMapper();
			response.setContentType( "application/json" );
			response.setCharacterEncoding( "UTF-8" );
			response.setStatus( HttpStatus.UNAUTHORIZED.value() );
			response.getWriter().write( mapper.writeValueAsString( result ) );
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
}

