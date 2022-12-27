package com.project.devowls.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.HistoryNumberDAO;
import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.PageInfo;

@Service
public class HistoryNumberServiceImpl implements HistoryNumberService {
	
	@Autowired
	HistoryNumberDAO hDAO;

	@Override
	public ArrayList<HistoryNumberVO> searchHistoryNumber(int page, PageInfo pageInfo) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		int listCount = hDAO.selectHistoryNumber();
		int maxPage = (int)Math.ceil((double)listCount/20);
		int startPage = (((int) ((double)page/10+0.9))-1)*10+1;
		int endPage = startPage+10-1;
		if(endPage>maxPage) endPage=maxPage;
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		int startrow = (page-1)*20+1;  // 페이지당 글 수 
		
		param.put("startrow", startrow);
		
		return hDAO.selecthHistoryNumber( param);
	}

	@Override
	public void insertHistoryNumber(HistoryNumberVO historyNumberVO) {
		// TODO Auto-generated method stub
		 hDAO.insertHistoryNumber( historyNumberVO);
	}

	@Override
	public ArrayList<HistoryNumberVO> searchHistoryNumberBySpawningDate(String spawningDate) {
		// TODO Auto-generated method stub
		return hDAO.selectHistoryNumberBySpawningDate(spawningDate);
	}

	@Override
	public ArrayList<String> searchHistoryNumberSucceeded() {
		// TODO Auto-generated method stub
		return hDAO.selectHistoryNumberSucceeded();
	}

}
