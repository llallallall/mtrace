package com.project.devowls.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleVO {
	
	private int    num;
	
	private String 	accountNm;
	private String 	businessNo;
	private String 	licenseNo;
	
	private String 	accountBusinessNo;	//거래처 사업자번호
	private String 	accountLicenseNo;	//거래처 인허가번호
	
	private int		eggXxl; 
	private int		eggXl; 
	private int		eggL; 
	private int		eggM; 
	private int		eggS; 
	private int		eggE; 
	
	private int		totalEgg;
	
	private int		totalDispose;		//총 폐기수량
	
	
	private String 	farmIdNo;
	private String 	eggUsage;
	private String 	storageMethod;
	private String 	washingMethod;
	private String 	farmUniqNo;
	private String 	clientBusinessNo;
	private String 	clientLicenseNo;
	private String 	breadingMethod;
	
	
	private String 	tradeTerm;
	private Date	regDate;
	private Date	modDate;
	
	private String 	packingBusinessNo;	//선별포장업 사업자번호
	private String 	packingLicenseNo;	//선별포장업 인허가번호
	
	private String 	tradeBusinessNo;	//수입판매업 사업자번호
	private String 	tradeLicenseNo;		//수집판매업 인허가번호

}
