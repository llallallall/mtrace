package com.project.devowls.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EggPackingVO {
	private int 	num;
	private Date 	reportTime;
	private String 	resultCode; 
	private String 	resultMsg; 
	
	private String 	eggHistNo; 
	private String 	requestDate; 	//선별포장 전송일자
	private String 	reportDate; 	//선별포장 등록 기준일자
	private String 	issueDate;   	//이력번호 발급일자
	
	private String 	businessNo; 
	private String 	licenseNo; 
	
	private String 	clientBusinessNo; 
	private String 	clientLicenseNo; 
	
	private String 	farmIdNo; 
	private String 	eggUsage; 
	private String 	spawningDate; 
	private String 	storageMethod; 
	
	private int		eggXxl; 
	private int		eggXl; 
	private int		eggL; 
	private int		eggM; 
	private int		eggS; 
	private int		eggE; 
	private int		totalEgg; 
	
	private int		eggXxlDealt; 
	private int		eggXlDealt; 
	private int		eggLDealt; 
	private int		eggMDealt; 
	private int		eggSDealt; 
	private int		eggEDealt; 
	
	private int		totalDealt; 
	
	private String	washingMethod;
	private String	breedingMethod;
	private String	farmUniqNo;
	
	private int		eggXxlDispose; 
	private int		eggXlDispose; 
	private int		eggLDispose; 
	private int		eggMDispose; 
	private int		eggSDispose; 
	private int		eggEDispose; 
	
	private int		totalDispose;

}
