package com.project.devowls.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.devowls.service.ScheduleService;
import com.project.devowls.vo.ScheduleVO;

@Controller
public class ScheduleInfoController {
	
	
	@Autowired
	ScheduleService sService;
	
	
	
	@RequestMapping(value="/callEggInfoForSchedule", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callEggInfoForSchedule() {
		HashMap<String, Object> data = new HashMap<String, Object>();
	
		
		ScheduleVO vo =  sService.searchEggInfoForSchedule();
		
		data.put("infoList", vo);
		

		return data;
	}
	
	
	
	@RequestMapping(value="/callAccountListForSchedule", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callAccountListForEggTrade(@RequestParam(value="page",required=false, defaultValue="1") int page) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		

		ArrayList<ScheduleVO> voList = new ArrayList<ScheduleVO>();
		voList =  sService.searchAccountListForSchedule();
		
		data.put("accountList", voList);
		

		return data;
	}

}
