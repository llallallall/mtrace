package com.project.devowls.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.devowls.service.AccountService;
import com.project.devowls.service.EggPackingService;
import com.project.devowls.service.EggTradeService;
import com.project.devowls.service.HistoryNumberService;
import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.EggTradeDetailVO;
import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.PageInfo;

@Controller
public class EggTradeController {
	
	@Autowired
	HistoryNumberService hService;
	
	@Autowired
	EggPackingService eService;
	
	@Autowired
	EggTradeService tService;
	
	
	@RequestMapping(value="/callEggTradeSucceeded", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callEggTradeSucceeded() {
		HashMap<String, Object> data = new HashMap<String, Object>();

		//이력번호
		ArrayList<HistoryNumberVO> voList = hService.searchHistoryNumberSucceeded();
		JSONArray historyItem = new JSONArray();
		
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
			
			//System.out.println(df.format(vo.getEggL()));
			
			eventParams.put("title", "이력번호등록");
			eventParams.put("start", vo.getSpawningDate());
			eventParams.put("end", vo.getSpawningDate());
			eventParams.put("description", desc);
			
			eventParams.put("groupId", "history");
			
			eventParams.put("eggHistNo", vo.getEggHistNo());
			eventParams.put("reportDate", vo.getReportDate());
			eventParams.put("spawningDate", vo.getSpawningDate());
			
			eventParams.put("eggXxl", df.format(vo.getEggXxl()));
			eventParams.put("eggXl", df.format(vo.getEggXl()));
			eventParams.put("eggL", df.format(vo.getEggL()));
			eventParams.put("eggM", df.format(vo.getEggM()));
			eventParams.put("eggS", df.format(vo.getEggS()));
			eventParams.put("eggE", df.format(vo.getEggE()));
	
			
			historyItem.add(eventParams);
		}
		
		
		//선별포장실적
		ArrayList<EggPackingVO> voList2 = eService.searchEggPackingSucceeded();
		JSONArray packingItem = new JSONArray();
		
		for(EggPackingVO vo : voList2 ) {
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
			
			if (vo.getEggXxlDispose() > 0 ) {
				desc = desc + "왕란(폐기) : " + df.format(vo.getEggXxl()) + ", ";
			}
			
			if (vo.getEggXlDispose() > 0 ) {
				desc = desc + "특란(폐기) : " + df.format(vo.getEggXl()) + ", ";
			}
			
			if (vo.getEggLDispose() > 0 ) {
				desc = desc + "대란(폐기) : " + df.format(vo.getEggL()) + ", ";
			}
			
			if (vo.getEggMDispose() > 0 ) {
				desc = desc + "중란(폐기) : " + df.format(vo.getEggM()) + ", ";
			}
			
			if (vo.getEggSDispose() > 0 ) {
				desc = desc + "소란(폐기) : " + df.format(vo.getEggS()) + ", ";
			}
			
			if (vo.getEggEDispose() > 0 ) {
				desc = desc + "기타(폐기) : " + df.format(vo.getEggE()) + ", ";
			}
			
			desc = desc.substring(0, desc.length() - 2);
			
			eventParams.put("title", "선별포장신고");
			eventParams.put("start", vo.getSpawningDate());
			eventParams.put("end", vo.getSpawningDate());
			eventParams.put("description", desc);
			
			eventParams.put("groupId", "packing");
			
			eventParams.put("eggHistNo", vo.getEggHistNo());
			eventParams.put("issueDate", vo.getIssueDate());
			eventParams.put("requestDate", vo.getRequestDate());
			eventParams.put("spawningDate", vo.getSpawningDate());
			
			eventParams.put("eggUsage", vo.getEggUsage());
			eventParams.put("reporterBusinessNo", vo.getBusinessNo());
			eventParams.put("reporterLicenseNo", vo.getLicenseNo());
			

			eventParams.put("eggXxl", df.format(vo.getEggXxl()));
			eventParams.put("eggXl", df.format(vo.getEggXl()));
			eventParams.put("eggL", df.format(vo.getEggL()));
			eventParams.put("eggM", df.format(vo.getEggM()));
			eventParams.put("eggS", df.format(vo.getEggS()));
			eventParams.put("eggE", df.format(vo.getEggE()));
			
			eventParams.put("eggXxlDealt", df.format(vo.getEggXxlDealt()));
			eventParams.put("eggXlDealt", df.format(vo.getEggXlDealt()));
			eventParams.put("eggLDealt", df.format(vo.getEggLDealt()));
			eventParams.put("eggMDealt", df.format(vo.getEggMDealt()));
			eventParams.put("eggSDealt", df.format(vo.getEggSDealt()));
			eventParams.put("eggEDealt", df.format(vo.getEggEDealt()));
			
			eventParams.put("eggXxlDispose", df.format(vo.getEggXxlDispose()));
			eventParams.put("eggXlDispose", df.format(vo.getEggXlDispose()));
			eventParams.put("eggLDispose", df.format(vo.getEggLDispose()));
			eventParams.put("eggMDispose", df.format(vo.getEggMDispose()));
			eventParams.put("eggSDispose", df.format(vo.getEggSDispose()));
			eventParams.put("eggEDispose", df.format(vo.getEggEDispose()));
			
			eventParams.put("totalEgg", df.format(vo.getTotalEgg()));
			eventParams.put("totalDealt", df.format(vo.getTotalDealt()));
			eventParams.put("totalDispose", df.format(vo.getTotalDispose()));
			
			packingItem.add(eventParams);
		}
		
		//사육현황신고
		

		//출고신고
		

		// 집계
		JSONObject result = new JSONObject();
		result.put("history", historyItem);
		result.put("packaging", packingItem);
		result.put("breeding", null);
		result.put("shipment", null);
		
		data.put("eventList", result);

		return data;
	}

	@RequestMapping(value="/callEggTrade", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callEggTrade(@RequestParam(value="page",required=false, defaultValue="1") int page) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		PageInfo pageInfo = new PageInfo();	
		
		ArrayList<EggTradeVO> voList = new ArrayList<EggTradeVO>();
		voList =  tService.searchEggTrade(page,pageInfo);
		
		//System.out.println(pageInfo.getListCount());
		//System.out.println(pageInfo.getMaxPage());
		data.put("eventList", voList);
		data.put("pageInfo", pageInfo);

		return data;
	}
	
	@Autowired
	AccountService aService;
	
	@RequestMapping(value="/callAccountListForEggTrade", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callAccountListForEggTrade(@RequestParam(value="page",required=false, defaultValue="1") int page) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		PageInfo pageInfo = new PageInfo();	
		
		ArrayList<AccountVO> voList = new ArrayList<AccountVO>();
		voList =  tService.searchAccountListOnTradeUse(page,pageInfo);
		
		data.put("accountList", voList);
		data.put("pageInfo", pageInfo);

		return data;
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/registerEggTrade", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> registerEggTrade(@RequestBody Map<String, Object> map
//			@RequestParam(required = false, value = "acountData") List<EggTradeDetailVO> eggTradeDetailVO 
			//EggTradeVO eggTradeVO 
			) throws IOException {
		
		EggTradeVO reporterVO = new EggTradeVO();
		reporterVO.setHistNoIssueDate(		(String) map.get("histNoIssueDate"));
		reporterVO.setSpawningDate(			(String) map.get("spawningDate"));
		reporterVO.setPackingReportDate(	(String) map.get("packingReportDate"));
		reporterVO.setEggUsage(				(String) map.get("eggUsage"));
		reporterVO.setReporterBusinessNo(	(String) map.get("reporterBusinessNo"));
		reporterVO.setReporterLicenseNo(	(String) map.get("reporterLicenseNo"));
		
		ObjectMapper mapper = new ObjectMapper();
		EggTradeDetailVO tradeList = mapper.convertValue(map.get("eggTradeList"), EggTradeDetailVO.class);
		
		//List<EggTradeDetailVO> tradeList = (List<EggTradeDetailVO>)map.get("eggTradeList");
		
		System.out.println("sendAjax.getArray");
		//System.out.println(reporterVO.getEggTradeList().getClass().getSimpleName());
		System.out.println(tradeList);
		//System.out.println(reporterVO.getEggTradeList().size());
//	    for(int i=0; i < tradeList.size(); i++) {
//	        System.out.println(tradeList.get(i).getAccountBusinessNo());
//	        //JSONObject json = new JSONObject((Map) reporterVO.getEggTradeList().get(i));
//	        
//	    }
	    
//	    List<Map<String, Object>> li = (List<Map<String, Object>>) ((HashMap) tradeList).entrySet().stream().collect(Collectors.toList());
//	    System.out.println("List: " + li);
//	    
//	    for(int i=0, n=eggTradeList.getAccountBusinessNo().size(); i<n; i++) {
//	        System.out.println(eggTradeList.getAccountBusinessNo());
//	    }
		//System.out.println(eggTradeList.size());
		//System.out.println(eggTradeVO);
		//System.out.println(eggTradeList);
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		
//		//기존 정상 입력 값 여부 확인
//		ArrayList<EggTradeVO> voList = tService.searchEggTradeBySpawningDate(eggTradeVO.getSpawningDate());
//		for(EggTradeVO vo : voList) {
//			if ( vo.getResultCode().equals("INFO-0000") ) {
//				data.put("resultCode", "duplicate");
//				data.put("resultStr", "이미 등록되었습니다.");
//				return data;
//			}
//		}
//		
//		//
//		HistoryNumberVO voHistory = tService.searchEggTradeByHistNo(eggTradeVO.getEggHistNo());
//
//		eggTradeVO.setEggHistNo(voHistory.getEggHistNo());
//		System.out.println(eggTradeVO.getEggHistNo());
//		eggTradeVO.setBusinessNo(voHistory.getBusinessNo());
//		eggTradeVO.setLicenseNo(voHistory.getLicenseNo());
//		eggTradeVO.setEggUsage(voHistory.getEggUsage());
//		eggTradeVO.setReportDate(voHistory.getReportDate());
//		eggTradeVO.setSpawningDate(voHistory.getSpawningDate());
//		eggTradeVO.setStorageMethod(voHistory.getStorageMethod());
//		eggTradeVO.setEggXxl(voHistory.getEggXxl());
//		eggTradeVO.setEggXl(voHistory.getEggXl());
//		eggTradeVO.setEggL(voHistory.getEggL());
//		eggTradeVO.setEggM(voHistory.getEggM());
//		eggTradeVO.setEggS(voHistory.getEggS());
//		eggTradeVO.setEggE(voHistory.getEggE());
//		eggTradeVO.setWashingMethod(voHistory.getWashingMethod());
//		eggTradeVO.setBreedingMethod(voHistory.getBreedingMethod());
//		
//		//API 송신 후 결과 코드 받기
//		// 요청변수 설정
//    	String userId = "e00580";
//		String apiKey = "jknPQSbVwVZmycttrFFY";
//		String serviceKey = "addEggPackng";
//		String postUrl = "http://api.mtrace.go.kr/rest/dfts/trace/transParam";
//		String eggHistNoParam ="";
//				
//		eggHistNoParam= eggTradeVO.getBusinessNo()+"|"    	// 1.신고인 사업자등록번호
//         				+eggTradeVO.getLicenseNo()+"|"		// 2.신고인 인허가번호 
//         				+eggTradeVO.getEggHistNo()+"|"		// 3.이력번호
//         				+eggTradeVO.getReportDate()+"|"		// 4.발급일자
//         				+eggTradeVO.getReportDate()+"|"		// 5.의뢰일자
//         				
//						+""+"|"	// 								// 6.의뢰업체 유형
//         				+eggTradeVO.getBusinessNo()+"|"    	// 7.의뢰인 사업자등록번호
//         				+eggTradeVO.getLicenseNo()+"|"		// 8.의뢰인 인허가번호 
//         				+""+"|"									// 9.의뢰처 업체명 
//         				+""+"|"									// 10.의뢰처 대표자명 
//         				
//         				+""+"|"									// 11.의뢰처 휴대폰번호
//         				+""+"|"									// 12.의뢰처 전화번호
//         				+""+"|"									// 13.의뢰처 주소
//         				+""+"|"									// 14.의뢰처 HACCP
//         				+""+"|"									// 15.의뢰처 친환경
//         				
//         				+""+"|"									// 16.의뢰처 동물복지
//         				+""+"|"									// 17.의뢰처 무항생제
//         				+""+"|"									// 18.의뢰처 유기축산
//         				+eggTradeVO.getFarmIdNo()+"|"			// 19.농장식별번호
//         				+eggTradeVO.getFarmUniqNo()+"|"		// 20.농장고유번호
//         				
//         				+""+"|"									// 21.농장명
//         				+""+"|"									// 22.농장경영자명
//         				+""+"|"									// 23.농장 의약품사용여부
//         				+""+"|"									// 24.농장 HACCP
//         				+""+"|"									// 25.농장 친환경
//         				
//         				+""+"|"									// 26.농장 동물복지
//         				+""+"|"									// 27.농장 무항생제
//         				+""+"|"									// 28.농장 유기축산
//         				+eggTradeVO.getEggUsage()+"|"			// 29.계란의용도
//         				+eggTradeVO.getSpawningDate()+"|"		// 30.산란일자
//         				
//         				+""+"|"									// 31.산란주령
//         				
//         				+eggTradeVO.getEggXxl()+"|"			// 32.왕란
//						+eggTradeVO.getEggXl()+"|"			// 33.특란
//						+eggTradeVO.getEggL()+"|"				// 34.대란
//						+eggTradeVO.getEggM()+"|"				// 35.중란
//						+eggTradeVO.getEggS()+"|"				// 36.소란
//						+eggTradeVO.getEggE()+"|"				// 37.기타
//						
//						+""+"|"									// 38.등급
//						+eggTradeVO.getStorageMethod()+"|"	// 39.보관방법
//         				+""+"|"									// 40.보관방법기타
//         				
//						+""+"|"									// 41.유통기한
//						
//						+eggTradeVO.getEggXxlDealt()+"|"		// 42.왕란
//						+eggTradeVO.getEggXlDealt()+"|"		// 43.특란
//						+eggTradeVO.getEggLDealt()+"|"		// 44.대란
//						+eggTradeVO.getEggMDealt()+"|"		// 45.중란
//						+eggTradeVO.getEggSDealt()+"|"		// 46.소란
//						+eggTradeVO.getEggEDealt()+"|"		// 47.기타
//						
//						+(eggTradeVO.getEggXxlDispose()+
//						+eggTradeVO.getEggXlDispose()+
//						+eggTradeVO.getEggLDispose()+
//						+eggTradeVO.getEggMDispose()+
//						+eggTradeVO.getEggSDispose()+
//						+eggTradeVO.getEggEDispose())+"|"		// 48.폐기수량
//						
//						+"316001"+"|"							// 49.폐기방법 : 매몰 316001
//						+""+"|"									// 50.폐기기타내용
//
//						+eggTradeVO.getWashingMethod()+"|"	// 51.세척방법코드
//		
//						+eggTradeVO.getEggXxlDispose()+"|"	// 52.왕란(폐기)
//						+eggTradeVO.getEggXlDispose()+"|"		// 53.특란(폐기)
//						+eggTradeVO.getEggLDispose()+"|"		// 54.대란(폐기)
//						+eggTradeVO.getEggMDispose()+"|"		// 55.중란(폐기)
//						+eggTradeVO.getEggSDispose()+"|"		// 56.소란(폐기)
//						+eggTradeVO.getEggEDispose();			// 57.기타(폐기)
//						
//
//		JSONObject postParams = new JSONObject();
//
//		postParams.put("userId", userId);
//		postParams.put("apiKey", apiKey);
//		postParams.put("serviceKey", serviceKey);
//        
//        JSONObject transParam = new JSONObject();
//        transParam.put("transParam", eggHistNoParam);
//
//        JSONArray item = new JSONArray();
//        item.add(transParam);
//        
//        postParams.put("item", item);
//        
//        //System.out.println(postParams);
//    try{    
//        URL url = new URL(postUrl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//    	conn.setDoOutput(true);
//    	conn.setRequestMethod("POST");
//    	conn.setRequestProperty("Content-Type", "application/json");
//    	conn.setRequestProperty("Accept-Charset", "UTF-8"); 
//    	conn.setConnectTimeout(10000);
//    	conn.setReadTimeout(10000);
//    	
//    	OutputStream os = conn.getOutputStream();
//    	
//    	String postParamsString = postParams.toString();
//    	os.write(postParamsString.getBytes("UTF-8"));
//    	os.flush();
//		
//    	// 리턴된 결과 읽기
//    	String inputLine = null;
//    	StringBuffer outResult = new StringBuffer();
//    	BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//    	while ((inputLine = in.readLine()) != null) {
//    			outResult.append(inputLine);
//    		}
//        
//    	conn.disconnect();
//    		System.out.println("REST API End :: " + outResult.toString()) ;
//    		
//    		StringBuffer sb      =  new StringBuffer();
//    		sb.append(outResult);	
//    		DocumentBuilderFactory factory  =  DocumentBuilderFactory.newInstance();
//    		DocumentBuilder builder    =  factory.newDocumentBuilder();
//    		Document document     =  builder.parse(new InputSource(new StringReader(sb.toString())));
//    		
//    		NodeList resultCodeNodeList     =  document.getElementsByTagName("resultCode");
//    		Node resultCodeNode      =  resultCodeNodeList.item(0).getChildNodes().item(0);
//    		String resultCode =resultCodeNode.getNodeValue();
//    		System.out.println("resultCode :: " + resultCode) ;
//    		
//    		NodeList resultMsgNodeList     =  document.getElementsByTagName("resultMsg");
//    		Node resultMsgNode      =  resultMsgNodeList.item(0).getChildNodes().item(0);
//    		String resultMsg =resultMsgNode.getNodeValue();
//    		System.out.println("resultMsg :: " + resultMsg) ;
//    		
//    		data.put("resultCode", "success");
//    		data.put("resultStr", outResult.toString());
//    		
//    		
//    	
//    		// DB에 결과값 입력
//    		System.out.println(eggTradeVO.getRequestDate().length());
//    		eggTradeVO.setResultCode(resultCode);
//    		eggTradeVO.setResultMsg(resultMsg);
//    		tService.insertEggTrade(eggTradeVO);
//    		
//    	}catch(Exception e){
//    		data.put("resultCode", "error");
//	        data.put("resultStr", e.getMessage());
//	        e.printStackTrace();
//    	}
		
		//System.out.println(eggTradeVO.getEggHistNo());
		
		//System.out.println(eggTradeVO.getAccountBusinessNo());
		//System.out.println(eggTradeVO.getTransDate());
		
		System.out.println("+++++++++++++++++++++++++++++++++++			trans controller		+++++++++++++++++++++++++++++++++++");
		//System.out.println(eggTradeVO.getEggTradeDetailList());
		//tService.insertEggTrade(eggTradeVO);
		
		data.put("resultCode", "success");
		data.put("resultStr", "성공");
		
		return data;
	}

}
