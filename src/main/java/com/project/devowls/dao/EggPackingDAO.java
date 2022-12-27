package com.project.devowls.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.devowls.vo.EggPackingVO;

@Mapper
@Repository
public interface EggPackingDAO {

	ArrayList<EggPackingVO> selectEggPacking(HashMap<String, Object> param);

	void insertEggPacking(EggPackingVO eggPackingVO);

	ArrayList<EggPackingVO> selectEggPackingBySpawningDate(String spawningDate);

	ArrayList<EggPackingVO> selectEggPackingSucceeded();

	int selectEggPackingCnt();

}
