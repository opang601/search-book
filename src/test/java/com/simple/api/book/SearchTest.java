package com.simple.api.book;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.simple.api.book.common.domain.entity.SearchKeywordEntity;
import com.simple.api.book.common.domain.entity.UsersEntity;
import com.simple.api.book.common.domain.repository.SearchKeywordRepository;
import com.simple.api.book.common.domain.repository.UserRepository;
import com.simple.api.book.common.domain.vo.book.KakaoBookInfoVO;
import com.simple.api.book.web.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {
	
	@Autowired
    private RestTemplate restTemplate;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SearchKeywordRepository searchKeywordRepository;
	
	@Before
    public void initData() {
		UsersEntity user = new UsersEntity();
		user.setUserId("testId");
		user.setUserName("테스트");
		user.setUserPwd("1234");
		
		userService.regist(user);
    }
	
	/**
	 * <pre>
	 *	API연동 테스트
	 * </pre>
	 */
	@Test
	public void searchTestAndHistorySave() {
		String keyword = "java";
		int page = 1;
		int pageSize = 10;
		
		String url = "https://dapi.kakao.com/v3/search/book" + "?query={keyword}&page={page}&size={pageSize}";
	    
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	    headers.set("authorization", "KakaoAK ebbf17292b0b2dc77648c22e77571ef3");
	    //API 연동 조회
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<KakaoBookInfoVO> bookResult = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoBookInfoVO.class, keyword, page, pageSize);
		
	    assertThat(bookResult.getStatusCode(), is(HttpStatus.OK));
	}
	
	/**
	 * <pre>
	 *	검색어 저장 및 조회 테스트
	 * </pre>
	 */
	@Test
	public void searchHistorySaveTest() {
		SearchKeywordEntity keywordEntity= new SearchKeywordEntity();
		keywordEntity.setSearchKeyword("도서");
		keywordEntity.setRegDt(new Date());
		UsersEntity user = userRepository.findAll().get(0);
		keywordEntity.setUser(user);
		searchKeywordRepository.save(keywordEntity);
		
		//검색이력 조회
		List<SearchKeywordEntity> searchList =  user.getSearchList();
		assertThat( searchList , is(notNullValue()));
	}
	

}
