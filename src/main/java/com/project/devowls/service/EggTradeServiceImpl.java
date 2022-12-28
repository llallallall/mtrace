package com.project.devowls.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.EggTradeDAO;
import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.PageInfo;

@Service
public class EggTradeServiceImpl implements EggTradeService {
	

	@Autowired
	EggTradeDAO eDAO;

	@Override
	public ArrayList<EggTradeVO> searchEggTrade(int page, PageInfo pageInfo) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		int listCount = eDAO.selectEggTradeCnt();
		int maxPage = (int)Math.ceil((double)listCount/5);
		int startPage = (((int) ((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		if(endPage>maxPage) endPage=maxPage;
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		int startrow = (page-1)*5+1;  // 페이지당 글 수 
		
		param.put("startrow", startrow);
		
		return eDAO.selectEggTrade( param);
	}

	@Override
	public void insertEggTrade(EggTradeVO eggTradeVO) {
		// TODO Auto-generated method stub
		eDAO.insertEggTrade( eggTradeVO);
	}

	@Override
	public ArrayList<EggTradeVO> searchEggTradeBySpawningDate(String spawningDate) {
		// TODO Auto-generated method stub
		return eDAO.selectEggTradeBySpawningDate(spawningDate);
	}

	@Override
	public ArrayList<EggTradeVO> searchEggTradeSucceeded() {
		// TODO Auto-generated method stub
		return eDAO.selectEggTradeSucceeded();
	}

	@Override
	public HistoryNumberVO searchEggTradeByHistNo(String eggHistNo) {
		// TODO Auto-generated method stub
		return eDAO.selectEggTradeByHistNo(eggHistNo);
	}

}
