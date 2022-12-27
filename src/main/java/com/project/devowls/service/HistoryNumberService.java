package com.project.devowls.service;

import java.util.ArrayList;

import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.PageInfo;

public interface HistoryNumberService {

	ArrayList<HistoryNumberVO> searchHistoryNumber(int page, PageInfo pageInfo);

	void insertHistoryNumber(HistoryNumberVO historyNumberVO);

	ArrayList<HistoryNumberVO> searchHistoryNumberBySpawningDate(String spawningDate);

	ArrayList<HistoryNumberVO> searchHistoryNumberSucceeded();

}
