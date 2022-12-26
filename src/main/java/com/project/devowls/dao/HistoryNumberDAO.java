package com.project.devowls.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.devowls.vo.HistoryNumberVO;

@Mapper
@Repository
public interface HistoryNumberDAO {


	int selectHistoryNumber();

	ArrayList<HistoryNumberVO> selecthHistoryNumber(HashMap<String, Object> param);

	void insertHistoryNumber(HistoryNumberVO historyNumberVO);

	ArrayList<HistoryNumberVO> selectHistoryNumberBySpawningDate(String spawningDate);

}
