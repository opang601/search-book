package com.simple.api.book.common.domain.vo.book;

import java.util.List;

import lombok.Data;

@Data
public class KakaoBookInfoVO {
	        
	private KakaoMetaVO meta;
	private List<KakaoDetailBookInfoVO> documents;	//도서 데이터 
}
