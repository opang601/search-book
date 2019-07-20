package com.simple.api.book.common.domain.response;

import lombok.Getter;

@Getter
public enum ResultCode {
	SUCCESS("0","성공"),
	FAIL("1","실패");
	
	private String code;
	private String desc;
	
	private ResultCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	
}
