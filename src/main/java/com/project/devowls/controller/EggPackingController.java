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

import com.project.devowls.service.EggPackingService;
import com.project.devowls.service.HistoryNumberService;
import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.PageInfo;

@Controller
public class EggPackingController {
	
	@Autowired
	HistoryNumberService hService;
	
	@Autowired
	EggPackingService eService;
	
	
	@RequestMapping(value="/callEggPackingSucceeded", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callEggPackingSucceeded() {
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
			eventParams.put("reportDate", vo.getReportDate());			//이력번호 등록 - 등록일자(=산란일자)
			eventParams.put("requestDate", vo.getRequestDate());		//이력번혹 등록 - 의뢰일자
			
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
			eventParams.put("issueDate", vo.getIssueDate());		//이력번호 발급일자 = 이력번호 신고일자
			eventParams.put("requestDate", vo.getRequestDate());	//선별포장 신고 - 의뢰일자
			eventParams.put("reportDate", vo.getReportDate());		//선별포장 신고 - 등록일자
			eventParams.put("spawningDate", vo.getSpawningDate());	//산란일자
			
			eventParams.put("eggUsage", vo.getEggUsage());
			eventParams.put("reporterBusinessNo", vo.getClientBusinessNo());
			eventParams.put("reporterLicenseNo", vo.getClientLicenseNo());
			
			
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

	@RequestMapping(value="/callEggPacking", method=RequestMethod.GET, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> callEggPacking(@RequestParam(value="page",required=false, defaultValue="1") int page) {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		PageInfo pageInfo = new PageInfo();	
		
		ArrayList<EggPackingVO> voList = new ArrayList<EggPackingVO>();
		voList =  eService.searchEggPacking(page,pageInfo);
		
		//System.out.println(pageInfo.getListCount());
		//System.out.println(pageInfo.getMaxPage());
		data.put("eventList", voList);
		data.put("pageInfo", pageInfo);

		return data;
	}
	
	
	
	
	@RequestMapping(value="/registerEggPacking", method=RequestMethod.POST, produces = "application/json; charset=utf8")
	@ResponseBody
	public HashMap<String, Object> registerEggPacking(@ModelAttribute EggPackingVO eggPackingVO) throws IOException {
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		//기존 정상 입력 값 여부 확인
		ArrayList<EggPackingVO> voList = eService.searchEggPackingBySpawningDate(eggPackingVO.getSpawningDate());
		for(EggPackingVO vo : voList) {
			if ( vo.getResultCode().equals("INFO-0000") ) {
				data.put("resultCode", "duplicate");
				data.put("resultStr", "이미 등록되었습니다.");
				return data;
			}
		}
		
		//System.out.println(eggPackingVO.getRequestDate());
		HistoryNumberVO voHistory = eService.searchEggPackingByHistNo(eggPackingVO.getEggHistNo());

		eggPackingVO.setEggHistNo(voHistory.getEggHistNo());
		//System.out.println(eggPackingVO.getEggHistNo());
		eggPackingVO.setBusinessNo(voHistory.getBusinessNo());
		eggPackingVO.setLicenseNo(voHistory.getLicenseNo());
		
		eggPackingVO.setClientBusinessNo(voHistory.getClientBusinessNo());
		eggPackingVO.setClientLicenseNo(voHistory.getClientLicenseNo());
		
		eggPackingVO.setFarmIdNo(voHistory.getFarmIdNo());
		eggPackingVO.setEggUsage(voHistory.getEggUsage());
		eggPackingVO.setIssueDate(voHistory.getReportDate());  			//이력번호 발급일자 = 등록일자 = 산란일자
		
		
		System.out.println("================"+eggPackingVO.getReportDate() +"====================");
		
		eggPackingVO.setSpawningDate(voHistory.getSpawningDate());
		eggPackingVO.setStorageMethod(voHistory.getStorageMethod());
		eggPackingVO.setEggXxl(voHistory.getEggXxl());
		eggPackingVO.setEggXl(voHistory.getEggXl());
		eggPackingVO.setEggL(voHistory.getEggL());
		eggPackingVO.setEggM(voHistory.getEggM());
		eggPackingVO.setEggS(voHistory.getEggS());
		eggPackingVO.setEggE(voHistory.getEggE());
		eggPackingVO.setWashingMethod(voHistory.getWashingMethod());
		eggPackingVO.setBreedingMethod(voHistory.getBreedingMethod());
		eggPackingVO.setFarmUniqNo(voHistory.getFarmUniqNo());
		
		//API 송신 후 결과 코드 받기
		// 요청변수 설정
    	String userId = "e00580";
		String apiKey = "jknPQSbVwVZmycttrFFY";
		String serviceKey = "addEggPackng";
		String postUrl = "http://api.mtrace.go.kr/rest/dfts/trace/transParam";
		String eggHistNoParam ="";
				
		eggHistNoParam = eggPackingVO.getBusinessNo()+"|"    	// 1.신고인 사업자등록번호
         				+eggPackingVO.getLicenseNo()+"|"		// 2.신고인 인허가번호 
         				+eggPackingVO.getEggHistNo()+"|"		// 3.이력번호
         				+""+"|"									// 4.발급일자
//         				+eggPackingVO.getIssueDate()+"|"		// 4.발급일자
         				+""+"|"									// 5.의뢰일자
         				
						+""+"|"	// 								// 6.의뢰업체 유형
         				+eggPackingVO.getClientBusinessNo()+"|" // 7.의뢰인 사업자등록번호
         				+eggPackingVO.getClientLicenseNo()+"|"	// 8.의뢰인 인허가번호 
         				+""+"|"									// 9.의뢰처 업체명 
         				+""+"|"									// 10.의뢰처 대표자명 
         				
         				+""+"|"									// 11.의뢰처 휴대폰번호
         				+""+"|"									// 12.의뢰처 전화번호
         				+""+"|"									// 13.의뢰처 주소
         				+""+"|"									// 14.의뢰처 HACCP
         				+""+"|"									// 15.의뢰처 친환경
         				
         				+""+"|"									// 16.의뢰처 동물복지
         				+""+"|"									// 17.의뢰처 무항생제
         				+""+"|"									// 18.의뢰처 유기축산
         				+eggPackingVO.getFarmIdNo()+"|"			// 19.농장식별번호
         				+""+"|"									// 20.농장고유번호
//         				+eggPackingVO.getFarmUniqNo()+"|"		// 20.농장고유번호
         				
         				+""+"|"									// 21.농장명
         				+""+"|"									// 22.농장경영자명
         				+""+"|"									// 23.농장 의약품사용여부
         				+""+"|"									// 24.농장 HACCP
         				+""+"|"									// 25.농장 친환경
         				
         				+""+"|"									// 26.농장 동물복지
         				+""+"|"									// 27.농장 무항생제
         				+""+"|"									// 28.농장 유기축산
         				+eggPackingVO.getEggUsage()+"|"			// 29.계란의용도
         				+eggPackingVO.getSpawningDate()+"|"		// 30.산란일자
         				
         				+""+"|"									// 31.산란주령
         				
         				+eggPackingVO.getEggXxl()+"|"			// 32.왕란
						+eggPackingVO.getEggXl()+"|"			// 33.특란
						+eggPackingVO.getEggL()+"|"				// 34.대란
						+eggPackingVO.getEggM()+"|"				// 35.중란
						+eggPackingVO.getEggS()+"|"				// 36.소란
						+eggPackingVO.getEggE()+"|"				// 37.기타
						
						+""+"|"									// 38.등급
						+eggPackingVO.getStorageMethod()+"|"	// 39.보관방법
         				+""+"|"									// 40.보관방법기타
         				
						+""+"|"									// 41.유통기한
						
						+eggPackingVO.getEggXxlDealt()+"|"		// 42.왕란(처리)
						+eggPackingVO.getEggXlDealt()+"|"		// 43.특란(처리)
						+eggPackingVO.getEggLDealt()+"|"		// 44.대란(처리)
						+eggPackingVO.getEggMDealt()+"|"		// 45.중란(처리)
						+eggPackingVO.getEggSDealt()+"|"		// 46.소란(처리)
						+eggPackingVO.getEggEDealt()+"|"		// 47.기타(처리)
						
						
						+eggPackingVO.getTotalDispose()+"|"		// 48.폐기수량
						
//						+"316001"+"|"							// 49.폐기방법 : 매몰 316001
						+""+"|"									// 49.폐기방법
						+""+"|"									// 50.폐기기타내용

						+eggPackingVO.getWashingMethod()+"|"	// 51.세척방법코드
		
						+eggPackingVO.getEggXxlDispose()+"|"	// 52.왕란(폐기)
						+eggPackingVO.getEggXlDispose()+"|"		// 53.특란(폐기)
						+eggPackingVO.getEggLDispose()+"|"		// 54.대란(폐기)
						+eggPackingVO.getEggMDispose()+"|"		// 55.중란(폐기)
						+eggPackingVO.getEggSDispose()+"|"		// 56.소란(폐기)
						+eggPackingVO.getEggEDispose();			// 57.기타(폐기)
						

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
    		
    		
    		
    		
    	
    		// 성공 코드인 경우, DB에 결과값 입력
    		//System.out.println(eggPackingVO.getRequestDate().length());
    		if (resultCode.equals("INFO-0000")) {
    			eggPackingVO.setResultCode(resultCode);
        		eggPackingVO.setResultMsg(resultMsg);
        		eService.insertEggPacking(eggPackingVO);
        		
        		data.put("resultCode", "success");
        		data.put("resultStr", outResult.toString());
    		} else {
    			
    			data.put("resultCode", "error");
    	        data.put("resultStr", outResult.toString());
    			
    		}
    		
    		
    	}catch(Exception e){
    		data.put("resultCode", "error");
	        data.put("resultStr", e.getMessage());
	        e.printStackTrace();
    	}
		
		return data;
	}

}
