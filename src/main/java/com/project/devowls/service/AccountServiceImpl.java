package com.project.devowls.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.AccountDAO;
import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.PageInfo;

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

	@Override
	public ArrayList<AccountVO> searchAccountList(int page, PageInfo pageInfo) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		int listCount = aDAO.selectAccountCnt();
		int maxPage = (int)Math.ceil((double)listCount/10);
		int startPage = (((int) ((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		if(endPage>maxPage) endPage=maxPage;
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		int startrow = (page-1)*10+1;  // 페이지당 글 수 
		
		param.put("startrow", startrow);
	
		return aDAO.selectAccountList( param);
	}

	@Override
	public void updateTradeUse(AccountVO accountVO) {
		aDAO.updateTradeUse(accountVO);
		
	}

	@Override
	public void updateAccount(AccountVO accountVO) {
		aDAO.updateAccount(accountVO);
		
	}

	@Override
	public void deleteAccount(AccountVO accountVO) {
		aDAO.deleteAccount(accountVO);
		
	}

}
