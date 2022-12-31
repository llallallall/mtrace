package com.project.devowls.service;

import java.util.ArrayList;

import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.PageInfo;

public interface AccountService {

	int searchAccountByBusinessNo(AccountVO accountVO);

	void registerAccount(AccountVO accountVO);

	ArrayList<AccountVO> searchAccountList(int page, PageInfo pageInfo);

	void updateTradeUse(AccountVO accountVO);

	void updateAccount(AccountVO accountVO);

	void deleteAccount(AccountVO accountVO);

}
