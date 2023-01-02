package com.project.devowls.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EggTradeInfoVO {
	
	private int 	num;
	private Date 	reportTime;
	private String 	resultCode; 
	private String 	resultMsg; 
	
	//저장 용
	private String	eggHistIdx;
	private String 	eggHistNo; 			//이력번호
	
	private String 	packingReportDate; 
	private String 	histNoIssueDate; 
	
	private String 	storageMethod; 
	private String	washingMethod;
	private String	breedingMethod;
	private String 	businessNo;			//사업업자번호
	private String 	licenseNo;			//인허가번호

	//전송 용
	private String 	reporterBusinessNo; //신고인 사업자번호
	private String 	reporterLicenseNo; 	//신고인 인허가번호

	
	private String 	eggUsage; 			//계란의 용도
	private String 	spawningDate; 		//산란일자
	
	
	private int		transIdx;
	private Date	regDate;
	private Date	modDate;
	
	private String 	accountNm;			//거래처명
	private String 	accountBusinessNo;	//거래처 사업자번호
	private String 	accountLicenseNo;	//거래처 인허가번호
	
	private String	transType;			//거래유형 
	private String 	transDate;			//거래일자


	private int		eggXxl; 
	private int		eggXl; 
	private int		eggL; 
	private int		eggM; 
	private int		eggS; 
	private int		eggE; 
	
	private int		totalEgg;
	
	

}
