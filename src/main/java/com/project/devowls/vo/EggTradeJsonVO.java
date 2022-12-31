package com.project.devowls.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EggTradeJsonVO {
	
	private List<String> 	accountNm;			//거래처명
	private List<String> 	accountBusinessNo;	//거래처 사업자번호
	private List<String> 	accountLicenseNo;	//거래처 인허가번호
	
	private List<String>	transType;			//거래유형 
	private List<String> 	transDate;			//거래일자
	
	private List<String>	eggXxlDealtStr; 
	private List<String>	eggXlDealtStr; 
	private List<String>	eggLDealtStr; 
	private List<String>	eggMDealtStr; 
	private List<String>	eggSDealtStr; 
	private List<String>	eggEDealtStr; 
	
	private List<String>	eggTotalDealtStr; 

	private List<String>	eggXxl; 
	private List<String>	eggXl; 
	private List<String>	eggL; 
	private List<String>	eggM; 
	private List<String>	eggS; 
	private List<String>	eggE; 
	
	private List<String>	totalEgg;

}
