package com.project.devowls.vo;

import java.util.Date;

import lombok.Data;

@Data
public class HistoryNumberVO {
	private int 	num;
	private Date 	reportTime;
	private String 	resultCode; 
	private String 	resultMsg; 
	
	private String 	eggHistNo; 
	private String 	businessNo; 
	private String 	licenseNo; 
	private String 	farmIdNo; 
	private String 	eggUsage; 
	
	private String 	reportDate; 
	private String 	spawningDate; 
	private String 	storageMethod; 
	
	private int		eggXxl; 
	private int		eggXl; 
	private int		eggL; 
	private int		eggM; 
	private int		eggS; 
	private int		eggE; 
	
	private String	washingMethod;
	private String	breedingMethod;
	private String	farmUniqNo;

}
