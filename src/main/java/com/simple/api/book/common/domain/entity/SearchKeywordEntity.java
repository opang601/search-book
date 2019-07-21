package com.simple.api.book.common.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name="SEARCH_KWD")
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="srcIdx")
public class SearchKeywordEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SRC_IDX", unique=true, nullable=false)
	private Long srcIdx;					// 조회 고유번호
	
	@Column(name="SEARCH_KWD")
	private String searchKeyword;			// 검색어
	
	
	@Column(name="REG_DT", nullable=false)
	private LocalDateTime regDt;			// 조회

	@ManyToOne(targetEntity=UsersEntity.class, fetch=FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="user_idx")
    private UsersEntity user;
	
	
}
