package com.simple.api.book.common.domain.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Entity
@Table(name="USERS")
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="userIdx")
public class UsersEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_IDX", unique=true, nullable=false)
	private Long userIdx;		// 회원고유번호
	
	@Column(name="USER_ID")
	private String userId;		// 회원ID
	
	@Column(name="USER_PWD")
	private String userPwd;		// 회원비밀번호

	@JsonIgnore
	@Column(name="SALT")
	private String salt;		// 비밀번호 암호화 난수
	
	@Column(name="USER_NAME")
	private String userName;	// 회원이름
	
	@Column(name="REG_DT", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDt;			// 가입일시


	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private List<SearchKeywordEntity> searchList;
}
