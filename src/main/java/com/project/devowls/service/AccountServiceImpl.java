package com.project.devowls.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.AccountDAO;
import com.project.devowls.vo.AccountVO;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountDAO aDAO;
	
	@Override
	public int searchAccountByBusinessNo(AccountVO accountVO) {
		// TODO Auto-generated method stub
		return aDAO.selectAccountByBusinessNo(accountVO);
	}

	@Override
	public void registerAccount(AccountVO accountVO) {
		aDAO.insertAccount(accountVO);
		
	}

}
