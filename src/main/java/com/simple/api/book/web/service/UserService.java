package com.simple.api.book.web.service;

import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.response.Result;

public interface UserService {

	Result regist(UsersEntity user);

	Result idCheckUser(String userId);

	Result getUserList();

}
