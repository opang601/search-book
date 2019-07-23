package com.simple.api.book.common.domain.repository.custom;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.simple.api.book.common.domain.entity.SearchRankEntity;


@Repository
public interface SearchRankRepositoryCustom {
	
	List<SearchRankEntity> findByKeywordGroup(Date targetDate);
	
}
