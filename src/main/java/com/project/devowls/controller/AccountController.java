package com.project.devowls.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {
	
//	@Autowired
//	AccountService aService;
	
	@RequestMapping(value="/callAccountAdd", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> accountAdd(@RequestParam(value="page",required=false, defaultValue="1") int page) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
//		PageInfo pageInfo = new PageInfo();	
//		
//		ArrayList<EggTradeVO> voList = new ArrayList<EggTradeVO>();
//		voList =  aService.searchEggTrade(page,pageInfo);
//		
//		//System.out.println(pageInfo.getListCount());
//		//System.out.println(pageInfo.getMaxPage());
//		data.put("eventList", voList);
//		data.put("pageInfo", pageInfo);

		return data;
	}
	
	
	@RequestMapping(value="/callAccountMgmt", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> accountMgmt(@RequestParam(value="page",required=false, defaultValue="1") int page) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
//		PageInfo pageInfo = new PageInfo();	
//		
//		ArrayList<EggTradeVO> voList = new ArrayList<EggTradeVO>();
//		voList =  aService.searchEggTrade(page,pageInfo);
//		
//		//System.out.println(pageInfo.getListCount());
//		//System.out.println(pageInfo.getMaxPage());
//		data.put("eventList", voList);
//		data.put("pageInfo", pageInfo);

		return data;
	}

}
