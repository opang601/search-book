package com.simple.api.book.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.vo.book.SearchBookVO;
import com.simple.api.book.web.controller.service.SearchBookService;

@RestController
@RequestMapping("/api/search")
public class SearchBookController {
	
	@Autowired
	private SearchBookService searchBookService;
	
	@PostMapping("/book")
	public Result searchBook(@RequestBody SearchBookVO searchBook) {
		
		return searchBookService.searchBook(searchBook);
		
	}
}
