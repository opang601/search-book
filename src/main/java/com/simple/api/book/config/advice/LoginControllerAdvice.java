package com.simple.api.book.config.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.response.ResultCode;
import com.simple.api.book.config.exception.UnauthorizedException;

@ControllerAdvice
public class LoginControllerAdvice {

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result handleSecurityException(UnauthorizedException ue) {
        Result result = Result.failInstance();
		result.setStatusCode(HttpStatus.UNAUTHORIZED);
		result.setMessage(ResultCode.UNAUTHRIZED.getDesc());
        
        return result;
    }
}
