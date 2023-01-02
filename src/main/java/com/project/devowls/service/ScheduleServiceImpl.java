package com.project.devowls.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.ScheduleDAO;
import com.project.devowls.vo.ScheduleVO;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleDAO sDAO;

	@Override
	public ArrayList<ScheduleVO> searchAccountListForSchedule() {
		// TODO Auto-generated method stub
		return sDAO.selectAccountListForSchedule();
	}

	@Override
	public ScheduleVO searchEggInfoForSchedule() {
		// TODO Auto-generated method stub
		return sDAO.selectEggInfoForSchedule();
	}

	@Override
	public String searchScheduleTerm(String jobName) {
		// TODO Auto-generated method stub
		return sDAO.selectScheduleTerm(jobName);
	}

	@Override
	public String searchTriggerName(String jobName) {
		// TODO Auto-generated method stub
		return sDAO.selectTriggerName(jobName);
	}

	@Override
	public ScheduleVO searchScheduleInfo() {
		// TODO Auto-generated method stub
		return sDAO.selectScheduleInfo();
	}

	@Override
	public ArrayList<ScheduleVO> searchScheduleAccount() {
		// TODO Auto-generated method stub
		return sDAO.selectScheduleAccount();
	}

}
