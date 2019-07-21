package com.simple.api.book.common.domain.vo.book;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KakaoDetailBookInfoVO {
	//	제목, 도서 썸네일, 소개, ISBN, 저자, 출판사, 출판일, 정가, 판매가
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("thumbnail")
	private String thumbNail;
	
	@JsonProperty("contents")
	private String contents;
	
	@JsonProperty("isbn")
	private String isbn;
	
	@JsonProperty("authors")
	private String[] authors;
	
	@JsonProperty("publisher")
	private String pubCompany;
	
	@JsonProperty("datetime")
	private String pubDate;
	
	@JsonProperty("price")
	private int standPrice;
	
	@JsonProperty("sale_price")
	private int sellPrice;
	
}

//
//
//"authors": [
//            "기시미 이치로",
//            "고가 후미타케"
//        ],
//        "contents": "그의 고민에 “인간은 변할 수 있고, 누구나 행복해 질 수 있다. 단 그러기 위해서는 ‘용기’가 필요하다”고 말한 철학자가 있다. 바로 프로이트, 융과 함께 ‘심리학의 3대 거장’으로 일컬어지고 있는 알프레드 아들러다.  『미움받을 용기』는 아들러 심리학에 관한 일본의 1인자 철학자 기시미 이치로와 베스트셀러 작가인 고가 후미타케의 저서로, 아들러의 심리학을 ‘대화체’로 쉽고 맛깔나게 정리하고 있다. 아들러 심리학을 공부한 철학자와 세상에 부정적이고 열등감",
//        "datetime": "2016-01-08T00:00:00.000+09:00",
//        "isbn": " 480D160102560",
//        "price": 0,
//        "publisher": "인플루엔셜",
//        "sale_price": -1,
//        "status": "",
//        "thumbnail": "https://search1.kakaocdn.net/thumb/R120x174.q85/?fname=http%3A%2F%2Ft1.daumcdn.net%2Flbook%2Fimage%2F4650872%3Ftimestamp%3D20190302003654",
//        "title": "(체험판)미움받을 용기 음악이 들리는 eBook",
//        "translators": [
//            "전경아"
//        ],
//        "url": "https://search.daum.net/search?w=bookpage&bookId=4650872&q=%28%EC%B2%B4%ED%97%98%ED%8C%90%29%EB%AF%B8%EC%9B%80%EB%B0%9B%EC%9D%84+%EC%9A%A9%EA%B8%B0+%EC%9D%8C%EC%95%85%EC%9D%B4+%EB%93%A4%EB%A6%AC%EB%8A%94+eBook"
//    }