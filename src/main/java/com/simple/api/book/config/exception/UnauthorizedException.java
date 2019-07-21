package com.simple.api.book.config.exception;

import com.simple.api.book.common.domain.response.ResultCode;

public class UnauthorizedException extends RuntimeException{
	private static final long serialVersionUID = -2238030302650813813L;

	public UnauthorizedException() {
		super(ResultCode.UNAUTHRIZED.getDesc());
	}
	
	public UnauthorizedException(String message) {
		super(message);
	}
}
