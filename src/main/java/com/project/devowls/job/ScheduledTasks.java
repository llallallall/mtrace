package com.project.devowls.job;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.devowls.controller.EggPackingController;
import com.project.devowls.controller.EggTradeController;
import com.project.devowls.controller.HistoryNumberController;
import com.project.devowls.service.ScheduleService;
import com.project.devowls.vo.EggPackingVO;
import com.project.devowls.vo.EggTradeDetailVO;
import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.HistoryNumberVO;
import com.project.devowls.vo.MessageVO;
import com.project.devowls.vo.ScheduleVO;

@Controller
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
		mTraceAPI();
	}
	
	@RequestMapping(value="/callTestSchedule", method=RequestMethod.GET)
	@ResponseBody
	public void mTraceAPI() {
		System.out.println("Schedule Start :: " + formatter.format(LocalDateTime.now() ));
		
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
		
		int		totalEgg 				= eggXxl + eggXl + eggL + eggM + eggS + eggE;
		int 	totalDispose			= info.getTotalDispose();		//총 폐기수량

		
		//메시지 VO 저장
		MessageVO message = new MessageVO();
		
		System.out.println("[Schedule] 1. 이력번호 등록(addEggHistNo) :" + formatter.format(LocalDateTime.now() ));
		//=========================================================================//
  		//
		//		1. 이력번호 등록(addEggHistNo)
  		//
  		//=========================================================================//
		
		// 현재 날짜 = > 산란일자
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String spawningDate = now.format(formatter);
        String stdDate =  spawningDate ;
        
        Instant instant = now.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date reportTime = Date.from(instant);
        
        //이력번호 (산란일자(4)+농장고유번호(5)+사육방식(1)) : 0101OY2BC2
  		String histNo = spawningDate.substring(4,8) + farmUniqNo + breadingMethod;
  		System.out.println("[Schedule] 1.1 이력번호  :" + histNo);
  		HistoryNumberVO histVO = new HistoryNumberVO();
  		
  		histVO.setReportTime(reportTime);
  		histVO.setResultCode(null);
  		histVO.setResultMsg(null);
  		histVO.setEggHistNo(histNo);
  		
  		histVO.setBusinessNo(packingBusinessNo);
  		histVO.setLicenseNo(packingLicenseNo);
  		
  		histVO.setClientBusinessNo(tradeBusinessNo);
  		histVO.setClientLicenseNo(tradeLicenseNo);
  		
  		histVO.setFarmIdNo(farmIdNo);
  		histVO.setFarmUniqNo(farmUniqNo);
  		
  		histVO.setEggUsage(eggUsage);
  		histVO.setSpawningDate(spawningDate);
  		
  		histVO.setRequestDate(stdDate);
  		histVO.setReportDate(stdDate);
  		
  		histVO.setStorageMethod(storageMethod);
  		histVO.setWashingMethod(washingMethod);
  		histVO.setBreedingMethod(breadingMethod);
  		
  		histVO.setEggXxl(eggXxl);
  		histVO.setEggXl(eggXl);
  		histVO.setEggL(eggL);
  		histVO.setEggM(eggM);
  		histVO.setEggS(eggS);
  		histVO.setEggE(eggE);
  		
  		
  		// 메시지 발송용 저장 
  		message.setReportDate(stdDate.substring(0, 4)+"-"+stdDate.substring(4, 6)+"-"+stdDate.substring(6, 8));
  		message.setSpawningDate(spawningDate.substring(0, 4)+"-"+spawningDate.substring(4, 6)+"-"+spawningDate.substring(6, 8));
  		message.setHistNo(histNo);
  		message.setTotalEgg(totalEgg);
  		
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
  		
  		// 메시지 발송용 저장 
  		message.setHistNoResultCode(addEggHistNoRstCd);
  		
  		
  		System.out.println("[Schedule] 2. 선번포장실적 등록 :" + formatter.format(LocalDateTime.now() ));
  		//=========================================================================//
  		//
		//		2. 선번포장실적 등록
  		//
  		//=========================================================================//
  		EggPackingVO eggPackingVO = new EggPackingVO();
  		
  		eggPackingVO.setReportTime(reportTime);
  		eggPackingVO.setResultCode(null);
  		eggPackingVO.setResultMsg(null);
  		
  		eggPackingVO.setEggHistNo(histNo);
  		
  		eggPackingVO.setRequestDate(stdDate);
  		eggPackingVO.setReportDate(stdDate);
  		eggPackingVO.setIssueDate(stdDate);
  		
  		eggPackingVO.setBusinessNo(packingBusinessNo);
  		eggPackingVO.setLicenseNo(packingLicenseNo);
  		
  		eggPackingVO.setClientBusinessNo(tradeBusinessNo);
  		eggPackingVO.setClientLicenseNo(tradeLicenseNo);
  		
  		eggPackingVO.setFarmIdNo(farmIdNo);
  		eggPackingVO.setFarmUniqNo(farmUniqNo);
  		eggPackingVO.setSpawningDate(stdDate);
  		eggPackingVO.setStorageMethod(storageMethod);
  		
  		eggPackingVO.setWashingMethod(washingMethod);
  		eggPackingVO.setBreedingMethod(breadingMethod);
  		eggPackingVO.setEggUsage(eggUsage);
  		
  		

  		eggPackingVO.setEggXxl(eggXxl);
  		eggPackingVO.setEggXl(eggXl);
  		eggPackingVO.setEggL(eggL);
  		eggPackingVO.setEggM(eggM);
  		eggPackingVO.setEggS(eggS);
  		eggPackingVO.setEggE(eggE);
  		eggPackingVO.setTotalEgg(totalEgg);
  		
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
  		
  		// 메시지 발송용 저장 
  		message.setPackingResultCode(addEggPackngRstCd);
		
		
  		
  		System.out.println("[Schedule] 3. 거래처별 출고 신고(addEggTrade) :" + formatter.format(LocalDateTime.now() ));
  		//=========================================================================//
  		//
		//		3. 거래처별 출고 신고(addEggTrade)
  		//
  		//=========================================================================//
  		
  		EggTradeVO reporterVO = new EggTradeVO();
  		
  		reporterVO.setReportTime(reportTime);
  		reporterVO.setResultCode(null);
  		reporterVO.setResultMsg(null);
  		
  		reporterVO.setRequestDate(stdDate);
  		reporterVO.setReportDate(stdDate);
  		reporterVO.setIssueDate(stdDate);
  		
  		reporterVO.setPackingReportDate(stdDate);
  		reporterVO.setHistNoIssueDate(stdDate);
  		
  		reporterVO.setStorageMethod(storageMethod);
  		reporterVO.setWashingMethod(washingMethod);
  		reporterVO.setBreedingMethod(breadingMethod);
  		reporterVO.setBusinessNo(tradeBusinessNo);
  		reporterVO.setLicenseNo(tradeLicenseNo);
  		
  		reporterVO.setReporterBusinessNo(tradeBusinessNo);
  		reporterVO.setReporterLicenseNo(tradeLicenseNo);
  		
  		reporterVO.setEggHistNo(histNo);
  		reporterVO.setEggUsage(eggUsage);
  		reporterVO.setSpawningDate(spawningDate);
  		
  		
  		reporterVO.setEggXxlDealt(eggXxl);
  		reporterVO.setEggXlDealt(eggXl);
  		reporterVO.setEggLDealt(eggL-totalDispose);
  		reporterVO.setEggMDealt(eggM);
  		reporterVO.setEggSDealt(eggS);
  		reporterVO.setEggEDealt(eggE);
  		reporterVO.setTotalDealt(totalEgg-totalDispose);
  		
  		reporterVO.setTransInfo(null);
  		
  		
  		
  		
  		ArrayList<EggTradeDetailVO> tradeListVO = new ArrayList<EggTradeDetailVO>();
  		
  		
  		ArrayList<ScheduleVO> accountList = sService.searchScheduleAccount();
  		
  		
  		int accountSumEggXxl = 0;
		int accountSumEggXl = 0;
		int accountSumEggL = 0;
		int accountSumEggM = 0;
		int accountSumEggS = 0;
		int accountSumEggE = 0;
		
		
  		for(int i = 0; i < accountList.size(); i++ ) {
  			
  			accountSumEggXxl += accountList.get(i).getEggXxl();
  			accountSumEggXl += accountList.get(i).getEggXl();
  			accountSumEggL += accountList.get(i).getEggL();
  			accountSumEggM += accountList.get(i).getEggM();
  			accountSumEggS += accountList.get(i).getEggS();
  			accountSumEggE += accountList.get(i).getEggE();
  			
  		}
  		
  		int exceptLastEggXxl = 0;
  		int exceptLastEggXl = 0;
  		int exceptLastEggL = 0;
  		int exceptLastEggM = 0;
  		int exceptLastEggS = 0;
  		int exceptLastEggE = 0;
  		int exceptLastTotalEgg = 0;
  		
  		// 10의 배수로 재 계산
  		
  		eggL = eggL- totalDispose;
  		totalEgg = totalEgg-totalDispose;
  		
  		for(int i = 0; i < accountList.size(); i++ ) {
  			
  			accountList.get(i).setBusinessNo(tradeBusinessNo);
  			
  			accountList.get(i).setTransDate(stdDate);
  			
  			EggTradeDetailVO vo = new EggTradeDetailVO();
  			
  			vo.setAccountNm(accountList.get(i).getAccountNm());
  			vo.setAccountBusinessNo(accountList.get(i).getAccountBusinessNo());
  			vo.setAccountLicenseNo(accountList.get(i).getAccountLicenseNo());
  			
  			vo.setTransDate(stdDate);
  			vo.setTransType(accountList.get(i).getTransType());
  			
  			
  			int accoutEggXxl = accountSumEggXxl > 0 ? eggXxl*accountList.get(i).getEggXxl()/accountSumEggXxl : 0;
  			accoutEggXxl = ( accoutEggXxl % 10 > 0 ) ? (accoutEggXxl / 10) * 10 : accoutEggXxl;
  			vo.setEggXxl(accoutEggXxl);
  		
  			int accoutEggXl = accountSumEggXl>0 ? eggXl*accountList.get(i).getEggXl()/accountSumEggXl : 0;
  			accoutEggXl = ( accoutEggXl % 10 > 0 ) ? (accoutEggXl / 10) * 10 : accoutEggXl;
  			vo.setEggXl(accoutEggXl);
  			
  			System.out.println(" [재계산 "+i+ " ] 출고합계 EggXl : " + eggL);
  			System.out.println(" [재계산 "+i+ " ] 저장된 거래처 EggXl : " + accountList.get(i).getEggL());
  			System.out.println(" [재계산 "+i+ " ] 거래처별 합계 accountSumEggL : " + accountSumEggL );
  			int accoutEggL = accountSumEggL>0 ? eggL*accountList.get(i).getEggL()/accountSumEggL : 0;
  			accoutEggL = ( accoutEggL % 10 > 0 ) ? (accoutEggL / 10) * 10 : accoutEggL;
  			vo.setEggL(accoutEggL);
  			
  			System.out.println(" [재계산 "+i+ " ] 계산된 accoutEggL : " + accoutEggL );
  			int accoutEggM = accountSumEggM > 0 ? eggM*accountList.get(i).getEggM()/accountSumEggM : 0;
  			accoutEggM = ( accoutEggM % 10 > 0 ) ? (accoutEggM / 10) * 10 : accoutEggM;
  			vo.setEggM(accoutEggM);
  			
  			int accoutEggS = accountSumEggS > 0 ? eggS*accountList.get(i).getEggS()/accountSumEggS : 0;
  			accoutEggS = ( accoutEggS % 10 > 0 ) ? (accoutEggS / 10) * 10 : accoutEggS;
  			vo.setEggS(accoutEggS);
  			
  			int accoutEggE = accountSumEggE > 0 ? eggE*accountList.get(i).getEggE()/accountSumEggE : 0;
  			accoutEggE = ( accoutEggE % 10 > 0 ) ? (accoutEggE / 10) * 10 : accoutEggE;
  			vo.setEggE(accoutEggE);
  			
  			int accoutTotalEgg = accoutEggXxl + accoutEggXl + accoutEggL + accoutEggM + accoutEggS + accoutEggE ;
  			
  			vo.setTotalEgg(accoutTotalEgg ); 	
  			
  			if ( i < accountList.size() -1 ) {
  				 exceptLastEggXxl 	= exceptLastEggXxl 	+ accoutEggXxl;
  		  		 exceptLastEggXl 	= exceptLastEggXl 	+ accoutEggXl;
  		  		 exceptLastEggL 	= exceptLastEggL 	+ accoutEggL;
  		  		 exceptLastEggM 	= exceptLastEggM 	+ accoutEggM;
  		  		 exceptLastEggS 	= exceptLastEggS 	+ accoutEggS;
  		  		 exceptLastEggE 	= exceptLastEggE  	+ accoutEggE;
  		  		 exceptLastTotalEgg = exceptLastTotalEgg + accoutTotalEgg;
  		  		System.out.println(" 누적 수량 "+exceptLastEggL  );
  			} else {
  				vo.setEggXxl(eggXxl - exceptLastEggXxl > 0 ? eggXxl - exceptLastEggXxl  : 0);
  				vo.setEggXl(eggXl - exceptLastEggXl > 0 ? eggXl - exceptLastEggXl : 0);
  				vo.setEggL(eggL - exceptLastEggL > 0 ? eggL - exceptLastEggL : 0);
  				vo.setEggM(eggM - exceptLastEggM > 0 ? eggM - exceptLastEggM  : 0);
  				vo.setEggS(eggS - exceptLastEggS > 0 ? eggS - exceptLastEggS : 0);
  				vo.setEggE(eggE - exceptLastEggE > 0 ? eggE - exceptLastEggE : 0);
  				vo.setTotalEgg(totalEgg - exceptLastTotalEgg > 0 ? totalEgg - exceptLastTotalEgg : 0);
  				
  			}
  			System.out.println(" [출고된 총수량 "+totalEgg+ " ] 계산된 totalEgg : " + (exceptLastTotalEgg + vo.getTotalEgg()) );
  			tradeListVO.add(vo);
  		}

  		
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
  		
  		// 메시지 발송용 저장 
  		message.setTradeResultCode(addEggTradeRstCd);
  		
  		String transInfo = "";
  		
		for (int i=0; i < tradeListVO.size(); i++) {
			transInfo += tradeListVO.get(i).getAccountNm()+"("+tradeListVO.get(i).getTotalEgg()+")\n";
			System.out.println(tradeListVO.get(i).getEggHistIdx().substring(10, 13)+".\n"+ transInfo);
		};
		message.setTransInfo(transInfo);
		
		mailSender(message);
	}
	
	
	@RequestMapping(value="/callMailSender", method=RequestMethod.GET)
	@ResponseBody
	public void mailSender(MessageVO message) { 

		try {
				sService.sendMail(message);
			} catch(Exception e){
				e.printStackTrace();
			}
	}
	
	
}
     