package com.simple.api.book.common.domain.vo.book;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class KakaoMetaVO {
	
	@JsonProperty("pageable_count")
	private int pageableCount;			//페이지 사이즈
	@JsonProperty("total_count")
	private int totalCount;			//총 사이즈
	@JsonProperty("is_end")
	private boolean isEnd;			//마지막여부

}
