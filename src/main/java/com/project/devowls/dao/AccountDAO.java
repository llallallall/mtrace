package com.project.devowls.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.devowls.vo.AccountVO;

@Mapper
@Repository
public interface AccountDAO {

	int selectAccountByBusinessNo(AccountVO accountVO);

	void insertAccount(AccountVO accountVO);

	int selectAccountCnt();

	ArrayList<AccountVO> selectAccountList(HashMap<String, Object> param);

	void updateTradeUse(AccountVO accountVO);

	void updateAccount(AccountVO accountVO);

	void deleteAccount(AccountVO accountVO);

}
