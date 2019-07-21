package com.simple.api.book.web.controller.service;

import com.simple.api.book.common.domain.vo.user.LoginVO;

public interface AuthService {

	LoginVO login(String userId, String password);

	LoginVO refresh();

}
