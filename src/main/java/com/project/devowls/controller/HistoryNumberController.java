package com.project.devowls.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
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

import com.project.devowls.service.HistoryNumberService;
import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.PageInfo;

@Controller
public class HistoryNumberController {
	
	@Autowired
	HistoryNumberService hService;
	
	@RequestMapping(value="/callHistoryNumberSucceeded", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callHistoryNumberSucceeded() {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		ArrayList<HistoryNumberVO> voList = hService.searchHistoryNumberSucceeded();
		
		JSONArray historyItem = new JSONArray();
		
		
		//이력번호
		for(HistoryNumberVO vo : voList ) {
			JSONObject eventParams = new JSONObject();
			String desc="";
			DecimalFormat df = new DecimalFormat("###,###");
			
			if (vo.getEggXxl() > 0 ) {
				desc = desc + "왕란 : " + df.format(vo.getEggXxl()) + ", ";
			}
			
			if (vo.getEggXl() > 0 ) {
				desc = desc + "특란 : " + df.format(vo.getEggXl()) + ", ";
			}
			
			if (vo.getEggL() > 0 ) {
				desc = desc + "대란 : " + df.format(vo.getEggL()) + ", ";
			}
			
			if (vo.getEggM() > 0 ) {
				desc = desc + "중란 : " + df.format(vo.getEggM()) + ", ";
			}
			
			if (vo.getEggS() > 0 ) {
				desc = desc + "소란 : " + df.format(vo.getEggS()) + ", ";
			}
			
			if (vo.getEggE() > 0 ) {
				desc = desc + "기타 : " + df.format(vo.getEggE()) + ", ";
			}
			
			desc = desc.substring(0, desc.length() - 2);
			
			eventParams.put("title", "이력번호등록");
			eventParams.put("start", vo.getSpawningDate());
			eventParams.put("end", vo.getSpawningDate());
			eventParams.put("description", desc);
			
			historyItem.add(eventParams);
		}
		//선별포장실적

		
		//사육현황신고
		

		//출고신고
		

		// 집계
		JSONObject result = new JSONObject();
		result.put("history", historyItem);
		result.put("packaging", null);
		result.put("breeding", null);
		result.put("shipment", null);
		
		data.put("HistoryList", result);

		return data;
	}

	@RequestMapping(value="/callHistoryNumber", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callHistoryNumber(@RequestParam(value="page",required=false, defaultValue="1") int page) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		PageInfo pageInfo = new PageInfo();	
		
		ArrayList<HistoryNumberVO> voList = new ArrayList<HistoryNumberVO>();
		voList =  hService.searchHistoryNumber(page,pageInfo);
		
		System.out.println(pageInfo.getListCount());
		System.out.println(pageInfo.getMaxPage());
		data.put("totPropertiesList", voList);
		data.put("pageInfo", pageInfo);

		return data;
	}
	
	
	
	
	@RequestMapping(value="/registerHistoryNumber", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> registerHistoryNumber(@ModelAttribute HistoryNumberVO historyNumberVO) throws IOException {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		//기존 정상 입력 값 여부 확인
		ArrayList<HistoryNumberVO> voList = hService.searchHistoryNumberBySpawningDate(historyNumberVO.getSpawningDate());
		for(HistoryNumberVO vo : voList) {
			if ( vo.getResultCode().equals("INFO-0000") ) {
				data.put("resultCode", "duplicate");
				data.put("resultStr", "이미 등록되었습니다.");
				return data;
			}
		}
		
		//API 송신 후 결과 코드 받기
		// 요청변수 설정
    	String userId = "e00580";
		String apiKey = "jknPQSbVwVZmycttrFFY";
		String serviceKey = "addEggHistNo";
		String postUrl = "http://api.mtrace.go.kr/rest/dfts/trace/transParam";
		String eggHistNoParam ="";
		
		//이력번호 (산란일자(4)+농장고유번호(5)+사육방식(1))
		String histNo = historyNumberVO.getSpawningDate().substring(4,8) + historyNumberVO.getFarmUniqNo() + historyNumberVO.getBreedingMethod();
				
		eggHistNoParam= histNo+"|"								// 1.이력번호
		         		+historyNumberVO.getBusinessNo()+"|"    // 2.신고인 사업자등록번호
         				+historyNumberVO.getLicenseNo()+"|"		// 3.신고인 인허가번호 
         				+historyNumberVO.getReportDate()+"|"	// 4.발급일자
         				+historyNumberVO.getBusinessNo()+"|"    // 5.의뢰인 사업자등록번호
         				
         				+historyNumberVO.getLicenseNo()+"|"		// 6.의뢰인 인허가번호 
         				+""+"|"	// 7.의뢰처 업체명 
         				+""+"|"	// 8.의뢰처 대표자명 
         				+""+"|"	// 9.의뢰처 휴대폰번호
         				+""+"|"	// 10.의뢰처 전화번호
         				
         				+""+"|"	// 11.의뢰처 주소
         				+""+"|"	// 12.의뢰처 HACCP
         				+""+"|"	// 13.의뢰처 친환경
         				+""+"|"	// 14.의뢰처 동물복지
         				+""+"|"	// 15.의뢰처 무항생제
         				
         				+""+"|"		// 16.의뢰처 유기축산
         				+historyNumberVO.getFarmIdNo()+"|"		// 17.농장식별번호
         				+historyNumberVO.getFarmUniqNo()+"|"	// 18.농장고유번호
         				+""+"|"		// 19.농장명
         				+""+"|"		// 20.농장경영자명
         				+""+"|"		// 21.농장 의약품사용여부
         				+""+"|"		// 22.농장 HACCP
         				+""+"|"		// 23.농장 친환경
         				+""+"|"		// 24.농장 동물복지
         				+""+"|"		// 25.농장 무항생제
         				+""+"|"		// 26.농장 유기축산
         				+historyNumberVO.getEggUsage()+"|"		// 27.계란의용도
         				+historyNumberVO.getSpawningDate()+"|"	// 28.산란일자
         				+""+"|"		// 29.산란주령
         				+historyNumberVO.getStorageMethod()+"|"	// 30.보관방법
         				+""+"|"		// 31.보관방법기타
         				
						+""+"|"		// 32.유통기한
						+historyNumberVO.getEggXxl()+"|"		// 33.왕란
						+historyNumberVO.getEggXl()+"|"			// 34.특란
						+historyNumberVO.getEggL()+"|"			// 35.대란
						+historyNumberVO.getEggM()+"|"			// 36.중란
						+historyNumberVO.getEggS()+"|"			// 37.소란
						+historyNumberVO.getEggE()+"|"			// 38.기타
						
						+""+"|"		// 39.등급
						+historyNumberVO.getWashingMethod();	// 40.세척방법코드
						
		// OPEN API 호출 URL 정보 설정
		/*
		 {
				"userId": "openapitest",
				"apiKey": "openApiTestApiKey",
				"serviceKey": "addLot",
				"item": [{"transParam":"T1|1278523023|20040310305|L21811283336992|20190618|100.25|120”}]
		 }

		 * 
		 [{"transParam":"1029123411|8888888816|8888888816|20211102|8888888815|8888888815|대한유통|대한유통|01022222222|01022222222|세종시 가름로 232|Y|Y|Y|Y|Y|405422|12341|세종산란농장|장두|N|Y|Y|Y|Y|Y|314001|20211029|20|315001|기타|20211205|3|4|5|6|7|8|313001|238001"}]
		 * */
		JSONObject postParams = new JSONObject();

		postParams.put("userId", userId);
		postParams.put("apiKey", apiKey);
		postParams.put("serviceKey", serviceKey);
        
        JSONObject transParam = new JSONObject();
        transParam.put("transParam", eggHistNoParam);

        JSONArray item = new JSONArray();
        item.add(transParam);
        
        postParams.put("item", item);
        
        //System.out.println(postParams);
    try{    
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
    		
    		data.put("resultCode", "success");
    		data.put("resultStr", outResult.toString());
    		
    		
    	
    		// DB에 결과값 입력
    		historyNumberVO.setEggHistNo(histNo);
    		historyNumberVO.setResultCode(resultCode);
    		historyNumberVO.setResultMsg(resultMsg);
    		hService.insertHistoryNumber(historyNumberVO);
    		
    	}catch(Exception e){
    		data.put("resultCode", "error");
	        data.put("resultStr", e.getMessage());
	        e.printStackTrace();
    	}
		
		return data;
	}

}
