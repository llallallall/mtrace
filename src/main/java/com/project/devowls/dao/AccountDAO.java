package com.project.devowls.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.devowls.vo.AccountVO;

@Mapper
@Repository
public interface AccountDAO {

	int selectAccountByBusinessNo(AccountVO accountVO);

	void insertAccount(AccountVO accountVO);

}
