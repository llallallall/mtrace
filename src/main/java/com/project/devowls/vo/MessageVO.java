package com.project.devowls.vo;

import lombok.Data;

@Data
public class MessageVO {
	
	private String 	reportDate;
	private String 	spawningDate;
	
	private String 	histNo;
	private int		totalEgg;
	
	private String	histNoResultCode;
	private String	packingResultCode;
	private String	tradeResultCode;

	private String 	transInfo;
}
