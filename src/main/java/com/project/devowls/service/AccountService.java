package com.project.devowls.service;

import com.project.devowls.vo.AccountVO;

public interface AccountService {

	int searchAccountByBusinessNo(AccountVO accountVO);

	void registerAccount(AccountVO accountVO);

}
