package com.simple.api.book.web.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.simple.api.book.common.domain.entity.SearchRankEntity;
import com.simple.api.book.common.domain.repository.SearchRankRepository;
import com.simple.api.book.common.domain.repository.custom.SearchRankRepositoryCustom;

/**
 * <pre>
 *	검색어 순위 수집 스케쥴러
 * </pre>
 */
@Component
public class SearchRankScheduler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	static final long delayTime = 1000 * 10; //10초마다 검색테이블에서 순위조회 후 순위 테이블에 저장
	static final int limitRank = 10;		//검색어 순위 갯수
	
	@Autowired
	private SearchRankRepository searchRankRepository;
	@Autowired
	private SearchRankRepositoryCustom searchRankRepositoryCustom;
	
	@Scheduled(fixedDelay = delayTime) 
	public void rankJob() {
		logger.info("######## 인기 검색어 수집 스케쥴러 : {} ########", System.currentTimeMillis());
		
		try {
			Date date = new Date();
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			//30분전 데이터 Grouping
			cal.add(Calendar.MINUTE, -30);
			
			String targetDt = sdformat.format(cal.getTime());
			
			List<SearchRankEntity> rankList =  searchRankRepositoryCustom.findByKeywordGroup(sdformat.parse(targetDt));
			
			searchRankRepository.deleteAll();
			
			//지정된 갯수만큼만 순위에 저장
			rankList.stream().limit(limitRank)
						.forEach(l -> searchRankRepository.save(l));	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
   }
}