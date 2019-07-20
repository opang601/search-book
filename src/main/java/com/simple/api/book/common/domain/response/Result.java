package com.simple.api.book.common.domain.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class Result {

	private HttpStatus			statusCode;
	private String				message;
	private String				resultCode;
	private Object				data;

	public Result() {
	}

	public static Result successInstance() {
		return new Result().success();
	}
	public static Result failInstance() {
		return new Result().fail();
	}

	public Result success() {
		this.statusCode = HttpStatus.OK;
		this.message = ResultCode.SUCCESS.getDesc();
		this.resultCode = ResultCode.SUCCESS.getCode();
		return this;
	}

	public Result fail() {
		this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		this.message = ResultCode.FAIL.getDesc();
		this.resultCode = ResultCode.FAIL.getCode();
		return this;
	}

	public Result setStatusCode( HttpStatus statusCode ) {
		this.statusCode = statusCode;
		return this;
	}

	public Result setMessage( String message ) {
		this.message = message;
		return this;
	}

	public Result setData( Object data ) {
		this.data = data;
		return this;
	}

	public int getStatusCode() {
		return statusCode.value();
	}

}
