package com.project.devowls.service;

import java.util.ArrayList;

import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.MessageVO;
import com.project.devowls.vo.ScheduleVO;

public interface ScheduleService {

	ArrayList<ScheduleVO> searchAccountListForSchedule();

	ScheduleVO searchEggInfoForSchedule();

	String searchScheduleTerm(String jobName);

	String searchTriggerName(String jobName);

	ScheduleVO searchScheduleInfo();

	ArrayList<ScheduleVO> searchScheduleAccount();

	ArrayList<EggTradeVO> searchTradeInfoByHistNo();

	void sendMail(MessageVO mVO);

}
