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
	private String 	requestDate; 
	
	private String 	businessNo; 
	private String 	licenseNo; 
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
	
	private String	washingMethod;
	private String	breedingMethod;
	private String	farmUniqNo;
	
	private int		eggXxlDispose; 
	private int		eggXlDispose; 
	private int		eggLDispose; 
	private int		eggMDispose; 
	private int		eggSDispose; 
	private int		eggEDispose; 

}
