package com.simple.api.book.common.domain.vo.book;

import java.util.List;

import lombok.Data;

@Data
public class NaverBookInfoVO {
	        
	private int page;				//페이지
	private int pageSize;			//페이지 사이즈
	private int totalCount;			//총 사이즈

	private List<NaverDetailBookInfoVO> bookList;	//도서 데이터 
}
