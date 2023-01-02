package com.project.devowls.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.AccountDAO;
import com.project.devowls.dao.EggTradeDAO;
import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.EggTradeDetailVO;
import com.project.devowls.vo.EggTradeInfoVO;
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
	public void insertEggTrade(EggTradeVO eggTradeVO, ArrayList<EggTradeDetailVO> tradeListVO) {
		//메인항목 등록(1건)
		int idx = eDAO.selectEggHistIdx(eggTradeVO.getEggHistNo());
		
		
		//System.out.println("============================		trans master		==================================");
		//이력번호 인덱스 생성
		eggTradeVO.setEggHistIdx( eggTradeVO.getEggHistNo() + String.format("%03d", idx) );
		eDAO.insertEggTrade(eggTradeVO);

		//거래처별 상세항목 등록(여러건)
		//거래처수만큼 이력번호를 VO에 입력
		//System.out.println("============================		trans detail setting		==================================");
		int transIdx = eDAO.selectTransIdxByEggHistIdx(eggTradeVO.getEggHistIdx());
		for(int i=0; i< tradeListVO.size(); i++){
			tradeListVO.get(i).setEggHistIdx(eggTradeVO.getEggHistIdx());
			tradeListVO.get(i).setTransIdx(transIdx + i);
			
			//System.out.println(tradeListVO.get(i).getEggHistIdx());
			//System.out.println(tradeListVO.get(i).getTransIdx());
			eDAO.insertEggTradeByAccount(tradeListVO.get(i));
		}
		
	}

	
	
	@Override
	public HistoryNumberVO searchEggTradeByHistNo(String eggHistNo) {
		// TODO Auto-generated method stub
		return eDAO.selectEggTradeByHistNo(eggHistNo);
	}

	@Autowired
	AccountDAO aDAO;
	
	@Override
	public ArrayList<AccountVO> searchAccountListOnTradeUse(int page, PageInfo pageInfo) {
		
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
		
		
		return eDAO.selectAccountListOnTradeUse( param);
	}

	@Override
	public ArrayList<EggPackingVO> searchEggPackingByHistNo(String eggHistNo) {
		// TODO Auto-generated method stub
		return eDAO.selectEggPackingByHistNo( eggHistNo);
	}

	@Override
	public ArrayList<EggTradeVO> searchEggTradeSucceeded() {
		// TODO Auto-generated method stub
		return eDAO.selectEggTradeSucceeded();
	}

	@Override
	public ArrayList<EggTradeInfoVO> searchEggTradeRstByHistNo(String eggHistNo) {
		// TODO Auto-generated method stub
		return eDAO.selectEggTradeRstByHistNo( eggHistNo);
	}


}
