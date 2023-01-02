package com.project.devowls.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.devowls.vo.ScheduleVO;

@Mapper
@Repository
public interface ScheduleDAO {

	ArrayList<ScheduleVO> selectAccountListForSchedule();

	ScheduleVO selectEggInfoForSchedule();

	String selectScheduleTerm(String jobName);

	String selectTriggerName(String jobName);

	ScheduleVO selectScheduleInfo();

	ArrayList<ScheduleVO> selectScheduleAccount();

}
