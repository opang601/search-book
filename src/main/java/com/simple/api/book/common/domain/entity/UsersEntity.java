package com.simple.api.book.common.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="USERS")
@Data
public class UsersEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_IDX", unique=true, nullable=false)
	private Long userIdx;		// 회원고유번호
	
	@Column(name="USER_ID")
	private String userId;		// 회원ID
	
	@Column(name="USER_PWD")
	private String userPwd;		// 회원비밀번호
	
	@Column(name="USER_NAME")
	private String userName;	// 회원이름

	@Column(name="EMAIL")
	private String email;		// 이메일
	
	@Column(name="REG_DT", nullable=false)
	private LocalDateTime regDt;			// 가입일시

}
