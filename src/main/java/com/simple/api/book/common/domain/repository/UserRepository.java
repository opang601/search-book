package com.simple.api.book.common.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.simple.api.book.common.domain.entity.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository <UsersEntity, String>, JpaSpecificationExecutor<UsersEntity> {

}
