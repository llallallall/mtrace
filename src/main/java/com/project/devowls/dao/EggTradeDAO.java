package com.project.devowls.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.HistoryNumberVO;

@Mapper
@Repository
public interface EggTradeDAO {

	ArrayList<EggTradeVO> selectEggTrade(HashMap<String, Object> param);

	void insertEggTrade(EggTradeVO eggTradeVO);

	ArrayList<EggTradeVO> selectEggTradeBySpawningDate(String spawningDate);

	int selectEggTradeCnt();

	HistoryNumberVO selectEggTradeByHistNo(String eggHistNo);

	ArrayList<AccountVO> selectAccountListOnTradeUse(HashMap<String, Object> param);

	void insertEggTradeByAccount(EggTradeVO eggTradeVO);

	int selectEggHistIdx(String eggHistNo);

	int selectTransIdxByEggHistIdx(String eggHistIdx);

}
