package com.simple.api.book.web.controller.service.impl;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.simple.api.book.common.domain.entity.SearchKeywordEntity;
import com.simple.api.book.common.domain.repository.SearchKeywordRepository;
import com.simple.api.book.common.domain.repository.UserRepository;
import com.simple.api.book.common.domain.response.Result;
import com.simple.api.book.common.domain.vo.book.KakaoBookInfoVO;
import com.simple.api.book.common.domain.vo.book.SearchBookVO;
import com.simple.api.book.web.controller.service.JwtService;
import com.simple.api.book.web.controller.service.SearchBookService;

@Service
public class SearchBookServiceImpl implements SearchBookService{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private Environment env;
	
	@Value("${kakao.url}")
	private String kakaoUrl;
	@Value("${naver.url}")
	private String naverUrl;
	@Value("${kakao.authorization}")
	private String kakaoAuth;
	@Value("${naver.clientId}")
	private String naverClientId;
	@Value("${naver.clientSecret}")
	private String naverClientSecret;

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SearchKeywordRepository searchKeywordRepository;
	@Autowired
	private JwtService jwtService;
	
	@Override
	public Result searchBook(SearchBookVO searchBookVO) {
		Result result = Result.successInstance();
		try {
			result = requestBookQuery(searchBookVO);
			SearchKeywordEntity keyword = new SearchKeywordEntity();
			keyword.setSearchKeyword(searchBookVO.getSearchKeyword());
			keyword.setUser(jwtService.getUser());
			
			LocalDateTime currentDateTime = LocalDateTime.now();
			keyword.setRegDt(currentDateTime);
			
			searchKeywordRepository.save(keyword);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public Result requestBookQuery(SearchBookVO searchBookVO) {
		String keyword = searchBookVO.getSearchKeyword();
		int page = searchBookVO.getPage();
		int pageSize = searchBookVO.getPageSize();
		
		String url = env.getRequiredProperty("kakao.url") + "?query={keyword}&page={page}&size={pageSize}";
	    
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	    headers.set("Authorization", kakaoAuth);
	    
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<KakaoBookInfoVO> bookResult = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoBookInfoVO.class, keyword, page, pageSize);
	    
	    Result result = Result.successInstance();
	    result.setData(bookResult);
	    
	    return result;
	}
}






