package com.project.devowls.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.EggPackingDAO;
import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.PageInfo;

@Service
public class EggPackingServiceImpl implements EggPackingService {
	

	@Autowired
	EggPackingDAO eDAO;

	@Override
	public ArrayList<EggPackingVO> searchEggPacking(int page, PageInfo pageInfo) {
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		
		int listCount = eDAO.selectEggPackingCnt();
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
		
		return eDAO.selectEggPacking( param);
	}

	@Override
	public void insertEggPacking(EggPackingVO eggPackingVO) {
		// TODO Auto-generated method stub
		eDAO.insertEggPacking( eggPackingVO);
	}

	@Override
	public ArrayList<EggPackingVO> searchEggPackingBySpawningDate(String spawningDate) {
		// TODO Auto-generated method stub
		return eDAO.selectEggPackingBySpawningDate(spawningDate);
	}

	@Override
	public ArrayList<EggPackingVO> searchEggPackingSucceeded() {
		// TODO Auto-generated method stub
		return eDAO.selectEggPackingSucceeded();
	}

}
