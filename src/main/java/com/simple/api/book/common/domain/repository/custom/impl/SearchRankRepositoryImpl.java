package com.simple.api.book.common.domain.repository.custom.impl;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import com.querydsl.core.types.Projections;
import com.simple.api.book.common.domain.entity.QSearchKeywordEntity;
import com.simple.api.book.common.domain.entity.SearchRankEntity;
import com.simple.api.book.common.domain.repository.custom.SearchRankRepositoryCustom;

public class SearchRankRepositoryImpl  extends QueryDslRepositorySupport implements SearchRankRepositoryCustom{

	public SearchRankRepositoryImpl() {
		super(SearchRankEntity.class);
	}
	
	@Override
	public List<SearchRankEntity> findByKeywordGroup(Date targetDate) {
		QSearchKeywordEntity searchKeywordEntity = QSearchKeywordEntity.searchKeywordEntity;
		
		return from(searchKeywordEntity)
				.where(searchKeywordEntity.regDt.goe(targetDate))
				.groupBy(searchKeywordEntity.searchKeyword)
			    .select(Projections.bean(SearchRankEntity.class, searchKeywordEntity.searchKeyword, searchKeywordEntity.searchKeyword.count().as("count")))
			    .orderBy(searchKeywordEntity.searchKeyword.count().desc())
				.fetch();
		
	}

	
	
}