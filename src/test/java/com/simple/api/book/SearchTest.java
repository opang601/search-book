package com.simple.api.book;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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

import com.simple.api.book.common.domain.vo.book.KakaoBookInfoVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Test
	public void searchTest() {
		String keyword = "java";
		int page = 1;
		int pageSize = 10;
		
		String url = "https://dapi.kakao.com/v3/search/book" + "?query={keyword}&page={page}&size={pageSize}";
	    
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	    headers.set("authorization", "KakaoAK ebbf17292b0b2dc77648c22e77571ef3");
	    
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<KakaoBookInfoVO> bookResult = restTemplate.exchange(url, HttpMethod.GET, entity, KakaoBookInfoVO.class, keyword, page, pageSize);
		
	    logger.info("########" + bookResult.toString());
	    assertThat(bookResult.getStatusCode(), is(HttpStatus.OK));
	}
	
	

}
