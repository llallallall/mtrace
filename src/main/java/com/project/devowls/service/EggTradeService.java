package com.project.devowls.service;

import java.util.ArrayList;

import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.PageInfo;

public interface EggTradeService {

	ArrayList<EggTradeVO> searchEggTrade(int page, PageInfo pageInfo);

	void insertEggTrade(EggTradeVO eggTradeVO);

	ArrayList<EggTradeVO> searchEggTradeBySpawningDate(String spawningDate);

	

	HistoryNumberVO searchEggTradeByHistNo(String eggHistNo);

	ArrayList<AccountVO> searchAccountListOnTradeUse(int page, PageInfo pageInfo);

}
