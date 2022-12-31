package com.project.devowls.vo;

import java.util.Date;

import lombok.Data;

@Data
public class AccountVO {
	
	private int    num;
	
	private String businessNo;
	private String businessNoNext;
	
	private String accountNm;
	
	private String accountType;
	private String accountTypeNm;
	
	private String licenseNo;
	private String licenseNoNext;
	
	private String ceoNm;
	
	private String address;
	
	private String addrDetail;
	
	private String addrCd;
	
	private String postCd;
	
	private String addrType;
	private String addrTypeNm;
	
	private String telephone;
	
	private String mobile;
	
	private String fax;
	
	private String tradeUse;
	
	private Date regDate;
	
	private String code;
	
	private String remark;
}
