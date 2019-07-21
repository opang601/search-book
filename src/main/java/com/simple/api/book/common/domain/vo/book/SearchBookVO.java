package com.simple.api.book.common.domain.vo.book;

import lombok.Data;

@Data
public class SearchBookVO {
	        
	private String searchKeyword;	//검색어        
	private int page;				//페이지
	private int pageSize;			//페이지 사이즈
}
