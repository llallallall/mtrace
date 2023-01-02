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

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.project.devowls.service.AccountService;
import com.project.devowls.service.EggPackingService;
import com.project.devowls.service.EggTradeService;
import com.project.devowls.service.HistoryNumberService;
import com.project.devowls.vo.AccountVO;
import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.EggTradeDetailVO;
import com.project.devowls.vo.EggTradeInfoVO;
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
			
			eventParams.put("totalEgg", df.format(vo.getTotalEgg()));
			eventParams.put("totalDealt", df.format(vo.getTotalDealt()));
			eventParams.put("totalDispose", df.format(vo.getTotalDispose()));
			
			packingItem.add(eventParams);
		}
		
		//사육현황신고
		

		//출고신고
		ArrayList<EggTradeVO> voList3 = tService.searchEggTradeSucceeded();
		JSONArray tradeItem = new JSONArray();
		
		for(EggTradeVO vo : voList3 ) {
			
			JSONObject eventParams = new JSONObject();
			String desc="";
			DecimalFormat df = new DecimalFormat("###,###");
			
			eventParams.put("title", "출하신고");
			eventParams.put("start", vo.getPackingReportDate());
			eventParams.put("end", vo.getPackingReportDate());
			eventParams.put("description", vo.getTransInfo());
			//System.out.println( vo.getTransInfo());
			eventParams.put("groupId", "trade");
			
			eventParams.put("eggHistNo", vo.getEggHistNo());
			eventParams.put("histNoIssueDate", vo.getHistNoIssueDate());
			eventParams.put("packingReportDate", vo.getPackingReportDate());
			eventParams.put("spawningDate", vo.getSpawningDate());
			
			eventParams.put("eggUsage", vo.getEggUsage());
			eventParams.put("reporterBusinessNo", vo.getReporterBusinessNo());
			eventParams.put("reporterLicenseNo", vo.getReporterLicenseNo());

			tradeItem.add(eventParams);
		}
		
		
		

		// 집계
		JSONObject result = new JSONObject();
		result.put("history", historyItem);
		result.put("packaging", packingItem);
		result.put("breeding", null);
		result.put("shipment", tradeItem);
		
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
	

	@RequestMapping(value="/registerEggTrade", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> registerEggTrade( HttpServletRequest req ) throws IOException, ParseException {
		System.out.println("parsing Ajax Json");

		JSONObject jsonObject = readJSONStringFromRequestBody(req);

		String reporterBusinessNo = (String) jsonObject.get("reporterBusinessNo");
		String reporterLicenseNo = (String) jsonObject.get("reporterLicenseNo");
		
		String eggHistNo = (String) jsonObject.get("eggHistNo");
		String histNoIssueDate = (String) jsonObject.get("histNoIssueDate");
		String spawningDate = (String) jsonObject.get("spawningDate");
		String eggUsage = (String) jsonObject.get("eggUsage");
		
		String packingReportDate = (String) jsonObject.get("packingReportDate");
		String reportDate = (String) jsonObject.get("reportDate");
		String requestDate = (String) jsonObject.get("requestDate");

		EggTradeVO reporterVO = new EggTradeVO();
		reporterVO.setReporterBusinessNo(reporterBusinessNo);
		reporterVO.setReporterLicenseNo(reporterLicenseNo);
		reporterVO.setEggHistNo(eggHistNo);
		reporterVO.setHistNoIssueDate(histNoIssueDate);
		reporterVO.setSpawningDate(spawningDate);
		reporterVO.setEggUsage(eggUsage);
		reporterVO.setPackingReportDate(packingReportDate);
		reporterVO.setReportDate(reportDate);
		reporterVO.setRequestDate(requestDate);
		
		//System.out.println(reporterVO);

		ArrayList<EggTradeDetailVO> tradeListVO = new ArrayList<EggTradeDetailVO>();
		
		JSONArray eggTradeList = (JSONArray) jsonObject.get("eggTradeList");
		for(int i=0; i<eggTradeList.size(); i++){
			EggTradeDetailVO tradeVO = new EggTradeDetailVO();
			
			JSONObject result = (JSONObject) eggTradeList.get(i);
			//System.out.println("result :: " +result);
			String eggXxl = (String) result.get("eggXxl");
			String eggXl = (String) result.get("eggXl");
			String eggL = (String) result.get("eggL");
			String eggM = (String) result.get("eggM");
			String eggS = (String) result.get("eggS");
			String eggE = (String) result.get("eggE");
			String totalEgg = (String) result.get("totalEgg");
			
			tradeVO.setEggXxl( Integer.parseInt(eggXxl) );
			tradeVO.setEggXl( Integer.parseInt(eggXl) );
			tradeVO.setEggL( Integer.parseInt(eggL) );
			tradeVO.setEggM( Integer.parseInt(eggM) );
			tradeVO.setEggS( Integer.parseInt(eggS) );
			tradeVO.setEggE( Integer.parseInt(eggE) );
			tradeVO.setTotalEgg( Integer.parseInt(totalEgg) );
			
			String accountBusinessNo = (String) result.get("accountBusinessNo");
			String accountNm = (String) result.get("accountNm");
			String accountLicenseNo = (String) result.get("accountLicenseNo");
			
			tradeVO.setAccountBusinessNo(accountBusinessNo);
			tradeVO.setAccountNm(accountNm);
			tradeVO.setAccountLicenseNo(accountLicenseNo);
			
			String transDate = (String) result.get("transDate");
			String transType = (String) result.get("transType");
			
			tradeVO.setTransDate(transDate);
			tradeVO.setTransType(transType);
			
			tradeListVO.add(tradeVO);
		}
		
		//System.out.println("출고내역 :: " + tradeListVO);

		HashMap<String, Object> data = new HashMap<String, Object>();
		
		
		// 선별포장거래 신고 내역 - 정상 입력 값 여부 확인
		ArrayList<EggPackingVO> voList = tService.searchEggPackingByHistNo(eggHistNo);
		Boolean isPackingRst = false;
		for(EggPackingVO vo : voList) {
			//System.out.println("이력번호 조회 :: " + eggHistNo + vo.getResultCode());
			
			if ( vo.getResultCode().equals("INFO-0000") ) {
				isPackingRst = true;
			}
			
		}	
		
		// 선별포장거래 신고 내역 - 정상 입력 값 여부 확인
		ArrayList<EggTradeInfoVO> voList2 = tService.searchEggTradeRstByHistNo(eggHistNo);
		Boolean isTradeRst = false;
		for(EggTradeInfoVO vo : voList2) {
			//System.out.println("출고내역 조회 :: " + eggHistNo + vo.getResultCode());
			
			if ( vo.getResultCode().equals("INFO-0000") ) {
				isTradeRst = true;
			}
			
		}	
			
			
		
		
		if ( isPackingRst == true && isTradeRst == false ) {
			
			// 거래처 수만금 반복
			for(int i=0; i < tradeListVO.size(); i++) {
				
				
				// 정상 등록된 내역이 있는 경우
				//System.out.println("선별포장등록 이력번호 : " + eggHistNo);

				//API 송신 후 결과 코드 받기
				// 요청변수 설정
		    	String userId = "e00580";
				String apiKey = "jknPQSbVwVZmycttrFFY";
				String serviceKey = "addEggTrade";
				String postUrl = "http://api.mtrace.go.kr/rest/dfts/trace/transParam";
				String reporterParam ="";
				String tradeParam ="";
				
				reporterVO.setReporterBusinessNo(reporterBusinessNo);
				reporterVO.setReporterLicenseNo(reporterLicenseNo);
				reporterVO.setEggHistNo(eggHistNo);
				reporterVO.setHistNoIssueDate(histNoIssueDate);
				reporterVO.setSpawningDate(spawningDate);
				reporterVO.setEggUsage(eggUsage);
				reporterVO.setPackingReportDate(packingReportDate);
				reporterVO.setReportDate(reportDate);
				reporterVO.setRequestDate(requestDate);
				
				reporterParam	= reporterVO.getReporterBusinessNo()+"|"    	// 1.신고인 사업자등록번호(필수)
		         				+ reporterVO.getReporterLicenseNo()+"|"			// 2.신고인 인허가번호 	(필수)
		         				+ ""+"|";	// 									// 3.신고인 농장식별번호
		         				
		        tradeParam		= tradeListVO.get(i).getTransType()+"|"			// 4.거래구분	(필수)
		         				
								+tradeListVO.get(i).getTransDate()+"|"	 		// 5.거래일자 (필수)	
								+""+"|"	// 										// 6. 농장여부
		         				+""+"|"    										// 7.거래처 유형
		         				+tradeListVO.get(i).getAccountBusinessNo()+"|"	// 8.거래처 사업자번호 (필수)
		         				+tradeListVO.get(i).getAccountLicenseNo()+"|"	// 9.거래처 인허가번호 (필수)	
		         				+""+"|"											// 10.거래처 농장식별번호 
		         				
		         				+""+"|"											// 11.거래처 업체명
		         				+""+"|"											// 12.거래처 대표자명
		         				+""+"|"											// 13.거래처 휴대폰번호
		         				+""+"|"											// 14.거래처 전화번호
		         				+""+"|"											// 15.거래처 주소
		         				
		         				+reporterVO.getEggHistNo()+"|"					// 16.이력번호 (필수)	
		         				+reporterVO.getEggUsage()+"|"					// 17.계란의 용도 (필수) 
		         				+reporterVO.getSpawningDate()+"|"				// 18.산란일자 (필수)
		         				+tradeListVO.get(i).getEggXxl()+"|"				// 19.왕란
		         				+tradeListVO.get(i).getEggXl()+"|"				// 20.특란
		         				
		         				+tradeListVO.get(i).getEggL()+"|"				// 21.대란
		         				+tradeListVO.get(i).getEggM()+"|"				// 22.중란
		         				+tradeListVO.get(i).getEggS()+"|"				// 23.소란
		         				+tradeListVO.get(i).getEggE()+"|"				// 24.기타
		         				+""+"|"											// 25.등급
		         				
		         				+""+"|"											// 26.냉장출하여부
		         				+""+"|"											// 27.유통기한
		         				+""+"|"											// 28.폐기방법
		         				+""+"|"											// 29.폐기기타사유
		         				+"";											// 30.신고 일련번호
		         				
		         				

				JSONObject postParams = new JSONObject();

				postParams.put("userId", userId);
				postParams.put("apiKey", apiKey);
				postParams.put("serviceKey", serviceKey);
		        
		        JSONObject transParam = new JSONObject();
		        transParam.put("transParam", reporterParam+tradeParam);

		        JSONArray item = new JSONArray();
		        item.add(transParam);
		        
		        postParams.put("item", item);
		        
		        System.out.println("API 전송 파라미터 : "+postParams);
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

		    		System.out.println("+++++++++++++++++++++++++++++++++++			trans controller		+++++++++++++++++++++++++++++++++++");
		    		//System.out.println(eggTradeVO.getEggTradeDetailList());
		    		
		    		
		    		//System.out.println(reporterVO.getRequestDate().length());
		    		reporterVO.setResultCode(resultCode);
		    		reporterVO.setResultMsg(resultMsg);
		    		
		    		
		    		if ( resultCode.equals("INFO-0000") ) {
			    		data.put("resultCode", "success");
			    		data.put("resultStr", resultMsg);
		    		} else {
		    			data.put("resultCode", "error");
			    		data.put("resultStr", resultCode +"<br>"+resultMsg);	
		    		}
		    		
		    	}catch(Exception e){
		    		data.put("resultCode", "error");
			        data.put("resultStr", e.getMessage());
			        e.printStackTrace();
		    	}
				
			}  // for -- tradeList
			// DB에 결과값 입력
			tService.insertEggTrade(reporterVO, tradeListVO);
			
		} else {
			
			data.put("resultCode", "error");
			data.put("resultStr", "선별포장거래 신고내역이 없습니다.");
			return data;
			
		}
		
		return data;
	}
	
	
	
	// json 형식으로 유입된 HttpServletRequest를 string 형태로 return
    public  JSONObject readJSONStringFromRequestBody(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }

        }catch(Exception e) {
            //log.info("Error reading JSON string: " + e.toString());
            System.out.println("Error reading JSON string: " + e.toString());
        }
        JSONParser parser = new JSONParser();
        Object obj;
        JSONObject jsonObject = null;
		try {
			obj = parser.parse(json.toString());
			 jsonObject = (JSONObject) obj;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
		}
       
        return jsonObject;
    }

}
