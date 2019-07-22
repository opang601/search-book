package com.simple.api.book.web.scheduler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.simple.api.book.common.domain.entity.SearchKeywordEntity;
import com.simple.api.book.common.domain.entity.SearchRankEntity;
import com.simple.api.book.common.domain.repository.SearchKeywordRepository;
import com.simple.api.book.common.domain.repository.SearchRankRepository;

@Component
public class SearchRankScheduler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	static final long delayTime = 1000 * 10; //10초마다 검색테이블에서 순위조회 후 순위 테이블에 저장
	static final int limitRank = 10;		//검색어 순위 갯수
	
	@Autowired
	private SearchRankRepository searchRankRepository;
	@Autowired
	private SearchKeywordRepository searchKeywordRepository;
	
	@Scheduled(fixedDelay = delayTime) 
	public void rankJob() {
		logger.info("#################################");
		logger.info("스케쥴####################");
		List<SearchKeywordEntity> searchList = searchKeywordRepository.findAll();
		
		List<String> keywordList = new ArrayList<String>();
		for(SearchKeywordEntity tmp : searchList) {
			keywordList.add(tmp.getSearchKeyword());
		}
		
		Map<String, Long> groupMap = keywordList.stream()
	            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		List<SearchRankEntity> list = groupMap.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue()))
				.map(e -> new SearchRankEntity(e.getKey(), e.getValue())).collect(Collectors.toList());
		
		list.stream().limit(limitRank)
					.forEach(l -> searchRankRepository.save(l));	
	      
	   }
}