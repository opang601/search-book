package com.simple.api.book.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.vo.book.SearchBookVO;
import com.simple.api.book.web.service.SearchBookService;

@RestController
@RequestMapping("/api/search")
public class SearchBookController {
	
	@Autowired
	private SearchBookService searchBookService;
	
	/**	도서조회
	 * @param searchBook
	 * @return
	 */
	@PostMapping("/book")
	public Result searchBook(@RequestBody SearchBookVO searchBook) {
		
		return searchBookService.searchBook(searchBook);
		
	}

	/**	검색이력 조회
	 * @return
	 */
	@GetMapping("/history")
	public Result searchHistory() {
		
		return searchBookService.searchHistory();
		
	}

	/**	검색어 순위 조회
	 * @return
	 */
	@GetMapping("/rankHistory")
	public Result searchRankHistory() {
		
		return searchBookService.searchRankHistory();
		
	}
}
