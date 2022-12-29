package com.project.devowls.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.devowls.service.AccountService;
import com.project.devowls.vo.AccountVO;

@Controller
public class AccountController {
	
	@Autowired
	AccountService aService;
	
	@RequestMapping(value="/callRegisterAccount", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> accountAdd(@ModelAttribute AccountVO accountVO) throws IOException {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		//기존 사업자코드와 인허가번호 입력 값 여부 확인
		int accountCnt = aService.searchAccountByBusinessNo(accountVO);
		
		if ( accountCnt > 0 ) {
			data.put("resultCode", "duplicate");
			data.put("resultStr", "이미 등록되었습니다.");
			return data;
		}
		
		try {
			aService.registerAccount(accountVO);
			data.put("resultCode", "success");
	        data.put("resultStr", "성공적으로 등록되었습니다.");
			
		}catch(Exception e){
			data.put("resultCode", "error");
	        data.put("resultStr", e.getMessage());
	        e.printStackTrace();
		}
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
