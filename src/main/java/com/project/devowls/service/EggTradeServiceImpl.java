package com.project.devowls.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.AccountDAO;
import com.project.devowls.dao.EggTradeDAO;
import com.project.devowls.vo.AccountVO;
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
		//메인항목 등록(1건)
		int idx = eDAO.selectEggHistIdx(eggTradeVO.getEggHistNo());
		
		
		System.out.println("============================		trans master		==================================");
		//이력번호 인덱스 생성
		eggTradeVO.setEggHistIdx( eggTradeVO.getEggHistNo() + String.format("%03d", idx) );
		eDAO.insertEggTrade(eggTradeVO);
		
		System.out.println(eggTradeVO.getEggHistIdx());
		System.out.println(eggTradeVO.getPackingReportDate());
		System.out.println(eggTradeVO.getEggHistNo());
		System.out.println(eggTradeVO.getHistNoIssueDate());
		System.out.println(eggTradeVO.getEggUsage());
		System.out.println(eggTradeVO.getSpawningDate());
		System.out.println(eggTradeVO.getReporterBusinessNo());
		System.out.println(eggTradeVO.getReporterLicenseNo());

		
		//거래처별 상세항목 등록(여러건)
		//거래처수만큼 이력번호를 VO에 입력
		System.out.println("============================		trans detail		==================================");
		
		
		//List<EggTradeDetailVO> eggTradeDetailVO = eggTradeVO.getEggTradeDetailVO();
		
		
		//System.out.println(eggTradeDetailVO.size());
		//String[] accountArray = eggTradeVO.getAccountBusinessNo().split(",");
//		String strEggHistIdx = "";
//		for(int i=0; i < accountArray.length -1 ; i++) {
//			strEggHistIdx += eggTradeVO.getEggHistIdx() +',';
//		}
//		strEggHistIdx = strEggHistIdx +eggTradeVO.getEggHistIdx();
//		eggTradeVO.setEggHistIdx(strEggHistIdx);
//
//		//이력번호 인텍스 마다 거래번호 생성
//		int transIdx = eDAO.selectTransIdxByEggHistIdx(eggTradeVO.getEggHistIdx());
//		eggTradeVO.setTransIdx(transIdx);
//		System.out.println("============================		trans detail		==================================");
//		
//		System.out.println(eggTradeVO.getEggHistIdx());
//		System.out.println(eggTradeVO.getTransIdx());
//		System.out.println(eggTradeVO.getAccountNm());
//		System.out.println(eggTradeVO.getAccountBusinessNo());
//		System.out.println(eggTradeVO.getAccountLicenseNo());
//		System.out.println(eggTradeVO.getEggXxl());
//		System.out.println(eggTradeVO.getEggXl());
//		System.out.println(eggTradeVO.getEggL());
//		System.out.println(eggTradeVO.getEggM());
//		System.out.println(eggTradeVO.getEggS());
//		System.out.println(eggTradeVO.getEggE());
//		System.out.println(eggTradeVO.getTotalEgg());
//		System.out.println(eggTradeVO.getTransDate());
//		System.out.println(eggTradeVO.getTransDate());

		
		
		
		
		
		
		//eDAO.insertEggTradeByAccount(eggTradeVO);
	}

	@Override
	public ArrayList<EggTradeVO> searchEggTradeBySpawningDate(String spawningDate) {
		// TODO Auto-generated method stub
		return eDAO.selectEggTradeBySpawningDate(spawningDate);
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

}
