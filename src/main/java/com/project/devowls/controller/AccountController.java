package com.project.devowls.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.project.devowls.service.AccountService;
import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.PageInfo;

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
		
		//API 송신 후 결과 코드 받기
				// 요청변수 설정
		    	String userId = "e00580";
				String apiKey = "jknPQSbVwVZmycttrFFY";
				String serviceKey = "addEggTradeEntrp";   //(11) 계란 거래처 등록, 수정
				String postUrl = "http://api.mtrace.go.kr/rest/dfts/trace/transParam";
				String accountParam ="";
				
				accountParam = 
								 "8609200580"+"|"						// 1.신고업체 사업자등록번호 (필수)
								+"20200401604"+"|"						// 2.신고업체 인허가번호 (필수)
								+""+"|"									// 3.신고업체 농장식별번호 (필수)

								+accountVO.getBusinessNo()+"|"			// 4.거래처 사업자등록번호 (필수)
								+accountVO.getLicenseNo()+"|"			// 5.거래처 인허가번호 (필수)
								+accountVO.getAccountType()+"|"			// 6.거래처 업체유형 (필수)

								+accountVO.getAccountNm()+"|"			// 7.거래처 업체 명 (필수)
								+accountVO.getCeoNm()+"|"				// 8.거래처 대표자 명 (필수)
								+""+"|"									// 9.거래처 법정동코드
								+accountVO.getPostCd()+"|"				// 10.거래처 우편번호 (필수)
								+accountVO.getAddress()+"|"				// 11.거래처 주소 (필수)
								+accountVO.getAddrDetail()+"|"			// 12.거래처 상세주소 (필수)
								+""+"|"									// 13.거래처 주소구분
								+""+"|"									// 14.거래처 전화번호
								+""+"|"									// 15.거래처 휴대폰번호
								+""+"|"									// 16.거래처 팩스번호
								
								+""+"|"									// 17.거래처 HACCP 인증 여부
								+""+"|"									// 18.거래처 친환경 인증 여부
								+""+"|"									// 19.거래처 동물복지 인증 여부
								+""+"|"									// 20.거래처 무항생제 인증 여부
								+""+"|"									// 21.거래처 유기축산 인증 여부
								+""+"|"									// 22.거래처 HACCP 인증 코드
								+""+"|"									// 23.거래처 친환경 인증 코드
								+""+"|"									// 24.거래처 동물복지 인증 코드
								+""+"|"									// 25.거래처 무항생제 인증 코드
								+""+"|"									// 26.거래처 유기축산 인증 코드
								+"I";    								// 27.등록, 수정 구분 (필수)   //I : 등록(거래처 정보 신규 등록) U : 수정(거래처 정보 수정)

								
				
				JSONObject postParams = new JSONObject();
				
				postParams.put("userId", userId);
				postParams.put("apiKey", apiKey);
				postParams.put("serviceKey", serviceKey);
				
				JSONObject transParam = new JSONObject();
		        transParam.put("transParam", accountParam);
		        
		        JSONArray item = new JSONArray();
		        item.add(transParam);
		        
		        postParams.put("item", item);
		try {
			
			//api 전송 후 코드 에 따라 분기
			URL url = new URL(postUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    	conn.setDoOutput(true);
	    	conn.setRequestMethod("POST");
	    	conn.setRequestProperty("Content-Type", "application/json");
	    	conn.setRequestProperty("Accept-Charset", "UTF-8"); 
	    	conn.setConnectTimeout(10000);
	    	conn.setReadTimeout(10000);
	    	
	    	OutputStream os = conn.getOutputStream();
	    	
	    	String postParamsString = postParams.toString();
	    	os.write(postParamsString.getBytes("UTF-8"));
	    	os.flush();
			
	    	// 리턴된 결과 읽기
	    	String inputLine = null;
	    	StringBuffer outResult = new StringBuffer();
	    	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
	    	while ((inputLine = in.readLine()) != null) {
	    			outResult.append(inputLine);
	    		}
	        
	    	conn.disconnect();
	    		System.out.println("REST API End :: " + outResult.toString()) ;
	    		
	    		StringBuffer sb      =  new StringBuffer();
	    		sb.append(outResult);	
	    		DocumentBuilderFactory factory  =  DocumentBuilderFactory.newInstance();
	    		DocumentBuilder builder    =  factory.newDocumentBuilder();
	    		Document document     =  builder.parse(new InputSource(new StringReader(sb.toString())));
	    		
	    		NodeList resultCodeNodeList     =  document.getElementsByTagName("resultCode");
	    		Node resultCodeNode      =  resultCodeNodeList.item(0).getChildNodes().item(0);
	    		String resultCode =resultCodeNode.getNodeValue();
	    		System.out.println("resultCode :: " + resultCode) ;
	    		
	    		NodeList resultMsgNodeList     =  document.getElementsByTagName("resultMsg");
	    		Node resultMsgNode      =  resultMsgNodeList.item(0).getChildNodes().item(0);
	    		String resultMsg =resultMsgNode.getNodeValue();
	    		System.out.println("resultMsg :: " + resultMsg) ;
	        
	        
    		// 성공 코드인 경우, DB에 결과값 입력
	    		if (resultCode.equals("INFO-0000")) {
				aService.registerAccount(accountVO);
				data.put("resultCode", "success");
		        data.put("resultStr", "성공적으로 등록되었습니다.");
        		
    		} else {
    			
    			data.put("resultCode", "error");
    			data.put("resultCd", resultCode);
        		data.put("resultStr", outResult.toString());
    			
    		}
			
			
			
		}catch(Exception e){
			data.put("resultCode", "error");
	        data.put("resultStr", e.getMessage());
	        e.printStackTrace();
		}
		return data;
	}
	
	
	@RequestMapping(value="/callAccountList", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> accountMgmt(@RequestParam(value="page",required=false, defaultValue="1") int page) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		PageInfo pageInfo = new PageInfo();	
		
		ArrayList<AccountVO> voList = aService.searchAccountList(page,pageInfo);


		data.put("accountList", voList);
		data.put("pageInfo", pageInfo);

		return data;
	}
	
	@RequestMapping(value="/callUpdateAccount", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> updateAccount(@ModelAttribute AccountVO accountVO) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		
		
		//ArrayList<AccountVO> voList = aService.searchAccountList(page,pageInfo);
		try {
			aService.updateAccount(accountVO);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("resultCode", "error");
			data.put("resultCd", "error");
			data.put("resultStr", e.getMessage());
		}
		
		//System.out.println(pageInfo.getListCount());
		//System.out.println(pageInfo.getMaxPage());
		data.put("resultCode", "success");
		data.put("resultStr", "성공적으로 변경되었습니다.");
		//data.put("pageInfo", pageInfo);

		return data;
	}
	
	
	@RequestMapping(value="/callUpdateTradeUse", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> updateTradeUse(@ModelAttribute AccountVO accountVO) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		
		
		//ArrayList<AccountVO> voList = aService.searchAccountList(page,pageInfo);
		try {
			aService.updateTradeUse(accountVO);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("resultCode", "error");
			data.put("resultStr", e.getMessage());
		}
		
		//System.out.println(pageInfo.getListCount());
		//System.out.println(pageInfo.getMaxPage());
		data.put("resultCode", "success");
		data.put("resultStr", "성공적으로 변경되었습니다.");
		//data.put("pageInfo", pageInfo);

		return data;
	}
	
	
	@RequestMapping(value="/callDeleteAccount", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> deleteAccount(@ModelAttribute AccountVO accountVO) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		
		
		//ArrayList<AccountVO> voList = aService.searchAccountList(page,pageInfo);
		try {
			aService.deleteAccount(accountVO);
			
		} catch(Exception e) {
			e.printStackTrace();
			data.put("resultCode", "error");
			data.put("resultCd", "error");
			data.put("resultStr", e.getMessage());
		}
		
		//System.out.println(pageInfo.getListCount());
		//System.out.println(pageInfo.getMaxPage());
		data.put("resultCode", "success");
		data.put("resultStr", "성공적으로 변경되었습니다.");
		//data.put("pageInfo", pageInfo);

		return data;
	}

}
