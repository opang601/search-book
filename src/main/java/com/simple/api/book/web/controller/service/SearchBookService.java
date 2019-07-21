package com.simple.api.book.web.controller.service;

import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.vo.book.SearchBookVO;

public interface SearchBookService {

	Result searchBook(SearchBookVO searchBook);

	Result searchHistory();

	Result searchRankHistory();


}
