package com.simple.api.book.web.scheduler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.simple.api.book.web.service.SearchBookService;

/**
 * <pre>
 *	검색어 순위 수집 스케쥴러
 *	스케쥴러는 테스트 환경상 빠른 확인을 위해 10초간격으로 실행
 * </pre>
 */
@Component
public class SearchRankScheduler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	static final long DELAY_TIME = 1000 * 10; //10초마다 검색테이블에서 순위조회 후 순위 테이블에 저장
	
	@Autowired
	private SearchBookService searchBookService;
	
	@Scheduled(fixedDelay = DELAY_TIME) 
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
			
			searchBookService.getSearchRankGroup(sdformat.parse(targetDt));
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
   }
}