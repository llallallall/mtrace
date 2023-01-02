package com.project.devowls.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EggTradeDetailVO {
	
	private int 	num;

	private String	eggHistIdx;
	
	private int		transIdx;
	private Date	regDate;
	private Date	modDate;
	
	private String 	accountNm;			//거래처명
	private String 	accountBusinessNo;	//거래처 사업자번호
	private String 	accountLicenseNo;	//거래처 인허가번호
	
	private String	transType;			//거래유형 
	private String 	transDate;			//거래일자
	
	private String	eggXxlDealtStr; 
	private String	eggXlDealtStr; 
	private String	eggLDealtStr; 
	private String	eggMDealtStr; 
	private String	eggSDealtStr; 
	private String	eggEDealtStr; 
	
	private String	eggTotalDealtStr; 

	private int		eggXxl; 
	private int		eggXl; 
	private int		eggL; 
	private int		eggM; 
	private int		eggS; 
	private int		eggE; 
	
	private int		totalEgg;

	
	
}
