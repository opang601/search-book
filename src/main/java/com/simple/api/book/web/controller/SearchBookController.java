package com.simple.api.book.web.controller;


import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.api.book.common.domain.entity.SearchKeywordEntity;
import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.repository.SearchKeywordRepository;
import com.simple.api.book.common.domain.repository.UserRepository;

@RestController
@RequestMapping("/api/search")
public class SearchBookController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SearchKeywordRepository searchKeywordRepository;
	
	@GetMapping("/list")
	public List<SearchKeywordEntity> getUserList() throws Exception {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		UsersEntity user = new UsersEntity();
		user.setUserId("choh");
		user.setUserName("오충환");
		user.setUserPwd("12345");
		user.setRegDt(currentDateTime);
		
		userRepository.save(user);
		logger.info("messge : {}", userRepository.count());
		logger.info("messge : {}", userRepository.findAll().get(0));
		
		SearchKeywordEntity searchKwd = new SearchKeywordEntity();
		searchKwd.setRegDt(currentDateTime);
		searchKwd.setSearchKeyword("검색어~");
		searchKwd.setUser(user);
		
		searchKeywordRepository.save(searchKwd);
		
		List<SearchKeywordEntity> list = searchKeywordRepository.findAll();
		
		logger.info("List : {}", list);
		
		return list;
		
	}
}
