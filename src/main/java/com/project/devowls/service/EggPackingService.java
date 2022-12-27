package com.project.devowls.service;

import java.util.ArrayList;

import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.PageInfo;

public interface EggPackingService {

	ArrayList<EggPackingVO> searchEggPacking(int page, PageInfo pageInfo);

	void insertEggPacking(EggPackingVO eggPackingVO);

	ArrayList<EggPackingVO> searchEggPackingBySpawningDate(String spawningDate);

	ArrayList<EggPackingVO> searchEggPackingSucceeded();

}
