package com.simple.api.book.common.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name="SEARCH_RANK")
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="srcIdx")
public class SearchRankEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RANK_IDX", unique=true, nullable=false)
	private Long rankIdx;				// 순위 고유번호
	
	@Column(name="SEARCH_KWD")
	private String searchKeyword;		// 검색어
	
	@Column(name="COUNT")
	private long count;					// 조회횟수
	
	
	public SearchRankEntity(String searchKeyword, long count) {
		this.searchKeyword = searchKeyword;
		this.count = count;
	}
}
