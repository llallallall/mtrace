package com.project.devowls.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EggTradeVO {
	private int 	num;
	private Date 	reportTime;
	private String 	resultCode; 
	private String 	resultMsg; 
	
	//공통
	private String 	requestDate; 
	private String 	reportDate; 
	private String 	issueDate; 
	
	private String 	packingReportDate; 
	private String 	histNoIssueDate; 
	
	private String 	storageMethod; 
	private String	washingMethod;
	private String	breedingMethod;
	private String 	businessNo;			//사업업자번호
	private String 	licenseNo;			//인허가번호

	//저장 용
	private String	eggHistIdx;

	//전송 용
	private String 	reporterBusinessNo; //신고인 사업자번호
	private String 	reporterLicenseNo; 	//신고인 인허가번호

	private String 	eggHistNo; 			//이력번호
	private String 	eggUsage; 			//계란의 용도
	private String 	spawningDate; 		//산란일자
	
	private int		eggXxlDealt; 
	private int		eggXlDealt; 
	private int		eggLDealt; 
	private int		eggMDealt; 
	private int		eggSDealt; 
	private int		eggEDealt; 
	
	private int		totalDealt; 
	
	//거래내역 
	
	private String 	transInfo;


}
