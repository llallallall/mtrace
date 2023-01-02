package com.project.devowls.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.EggTradeDetailVO;
import com.project.devowls.vo.EggTradeInfoVO;
import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.HistoryNumberVO;

@Mapper
@Repository
public interface EggTradeDAO {

	ArrayList<EggTradeVO> selectEggTrade(HashMap<String, Object> param);

	void insertEggTrade(EggTradeVO eggTradeVO);


	int selectEggTradeCnt();

	HistoryNumberVO selectEggTradeByHistNo(String eggHistNo);

	ArrayList<AccountVO> selectAccountListOnTradeUse(HashMap<String, Object> param);

	void insertEggTradeByAccount(EggTradeDetailVO tradeListVO);

	int selectEggHistIdx(String eggHistNo);

	int selectTransIdxByEggHistIdx(String eggHistIdx);

	ArrayList<EggPackingVO> selectEggPackingByHistNo(String eggHistNo);

	ArrayList<EggTradeVO> selectEggTradeSucceeded();

	ArrayList<EggTradeInfoVO> selectEggTradeRstByHistNo(String eggHistNo);

}
