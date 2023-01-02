package com.project.devowls.job;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.project.devowls.controller.EggPackingController;
import com.project.devowls.controller.EggTradeController;
import com.project.devowls.controller.HistoryNumberController;
import com.project.devowls.service.ScheduleService;
import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.EggTradeDetailVO;
import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.ScheduleVO;

@Component
public class ScheduledTasks {
	
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss:SSS");

	@Autowired
	ScheduleService sService;
	
	@Autowired
	HistoryNumberController hController;
	
	@Autowired
	EggPackingController pController;
	
	@Autowired
	EggTradeController tController;
	
	@Scheduled(cron = "#{getTestSchedule}", zone = "Asia/Seoul")
	public void callMTraceAPI() {
	    
		System.out.println("cron : 현재시간 - {}" + formatter.format(LocalDateTime.now() ));
	}
	
	public void test() {
		System.out.println("TEST " + formatter.format(LocalDateTime.now() ));
		
		//세팅정보 조회
		ScheduleVO info = sService.searchScheduleInfo();

		String 	eggUsage				= info.getEggUsage();		//계란의 용도
		String 	storageMethod			= info.getStorageMethod();	//저장방법
		String 	washingMethod			= info.getWashingMethod();	//세척방법
		String 	farmIdNo				= info.getFarmIdNo();		//농장 식별번호
		String 	farmUniqNo				= info.getFarmUniqNo();		//농장 고유번호
		String 	breadingMethod			= info.getBreadingMethod();		//사육방식
		
		String 	packingBusinessNo		= info.getPackingBusinessNo();	//선별포장업 사업자번호
		String 	packingLicenseNo		= info.getPackingLicenseNo();	//선별포장업 인허가번호
		
		String 	tradeBusinessNo			= info.getTradeBusinessNo();	//수입판매업 사업자번호
		String 	tradeLicenseNo			= info.getTradeLicenseNo();		//수집판매업 인허가번호
		
		int		eggXxl					= info.getEggXxl(); 			//왕란
		int		eggXl					= info.getEggXl(); 				//특란
		int		eggL					= info.getEggL(); 				//대란
		int		eggM					= info.getEggM(); 				//중란
		int		eggS					= info.getEggS(); 				//소란
		int		eggE					= info.getEggE(); 				//기타
		
		int		totalEgg				= info.getTotalEgg();			//총 출하량
		int 	totalDispose			= info.getTotalDispose();		//총 폐기수량

		
		
		//=========================================================================//
  		//
		//		1. 이력번호 등록(addEggHistNo)
  		//
  		//=========================================================================//
		
		// 현재 날짜 = > 산란일자
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String spawningDate = now.format(formatter);
        
        //이력번호 (산란일자(4)+농장고유번호(5)+사육방식(1)) : 0101OY2BC2
  		String histNo = spawningDate.substring(4,8) + farmUniqNo + breadingMethod;
		
  		HistoryNumberVO histVO = new HistoryNumberVO();
  		histVO.setEggHistNo(histNo);
  		histVO.setBusinessNo(packingBusinessNo);
  		histVO.setLicenseNo(packingLicenseNo);
  		histVO.setClientBusinessNo(tradeBusinessNo);
  		histVO.setClientLicenseNo(tradeLicenseNo);
  		histVO.setFarmIdNo(farmIdNo);
  		histVO.setFarmUniqNo(farmUniqNo);
  		histVO.setEggUsage(eggUsage);
  		histVO.setSpawningDate(spawningDate);
  		histVO.setStorageMethod(storageMethod);
  		histVO.setWashingMethod(washingMethod);
  		histVO.setEggXxl(eggXxl);
  		histVO.setEggXl(eggXl);
  		histVO.setEggL(eggL);
  		histVO.setEggM(eggM);
  		histVO.setEggS(eggS);
  		histVO.setEggE(eggE);
  		
  		HashMap<String, Object> addEggHistNoRst = new HashMap<String, Object>();
		try {
			addEggHistNoRst = hController.registerHistoryNumber(histVO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
		String addEggHistNoRstCd = (String) addEggHistNoRst.get("resultCode");
		String addEggHistNoRstStr = (String) addEggHistNoRst.get("resultStr");
  	   
  		System.out.println(addEggHistNoRstCd + "" + addEggHistNoRstStr);
  		
  		
  		
  		
  		
  		//=========================================================================//
  		//
		//		2. 선번포장실적 등록
  		//
  		//=========================================================================//
  		EggPackingVO eggPackingVO = new EggPackingVO();
  		
  		eggPackingVO.setEggHistNo(histNo);
  		eggPackingVO.setTotalDispose(totalDispose);
  		
  		eggPackingVO.setEggXxlDealt(eggXxl);
  		eggPackingVO.setEggXlDealt(eggXl);
  		eggPackingVO.setEggLDealt(eggL-totalDispose);
  		eggPackingVO.setEggMDealt(eggM);
  		eggPackingVO.setEggSDealt(eggS);
  		eggPackingVO.setEggEDealt(eggE);
  		eggPackingVO.setTotalDealt(totalEgg-totalDispose);
  		
  		eggPackingVO.setEggXxlDispose(0);
  		eggPackingVO.setEggXlDispose(0);
  		eggPackingVO.setEggLDispose(totalDispose);
  		eggPackingVO.setEggMDispose(0);
  		eggPackingVO.setEggSDispose(0);
  		eggPackingVO.setEggEDispose(0);
  		eggPackingVO.setTotalDispose(totalDispose);
  		
  		eggPackingVO.setTotalDispose(totalDispose);
  		
  		HashMap<String, Object> addEggPackngRst = new HashMap<String, Object>();
		try {
			addEggPackngRst = pController.registerEggPacking(eggPackingVO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String addEggPackngRstCd = (String) addEggPackngRst.get("resultCode");
		String addEggPackngRstStr = (String) addEggPackngRst.get("resultStr");
  	   
  		System.out.println(addEggPackngRstCd + "" + addEggPackngRstStr);
  		
		
		
  		
  		
  		//=========================================================================//
  		//
		//		3. 거래처별 출고 신고(addEggTrade)
  		//
  		//=========================================================================//
  		
  		ArrayList<ScheduleVO> accountList = sService.searchScheduleAccount();
  		EggTradeVO reporterVO = new EggTradeVO();
  		
  		ArrayList<EggTradeDetailVO> tradeListVO = new ArrayList<EggTradeDetailVO>();
  		
  		String mode = "auto";
  		HashMap<String, Object> addEggTradeRst = new HashMap<String, Object>();
		try {
			addEggTradeRst = tController.registerEggTrade(null, reporterVO, tradeListVO, mode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String addEggTradeRstCd = (String) addEggTradeRst.get("resultCode");
		String addEggTradeRstStr = (String) addEggTradeRst.get("resultStr");
  	   
  		System.out.println(addEggTradeRstCd + "" + addEggTradeRstStr);
  		
		
	}
	
	
	
	
	
}
     