package com.project.devowls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/historyNumber")
	public String historyNumber() {
		return "historyNumber";
	}
	
	@GetMapping("/packagingPerformance")
	public String packagingPerformance() {
		return "packagingPerformance";
	}
	
	@GetMapping("/breedingStatus")
	public String breedingStatus() {
		return "breedingStatus";
	}
	
	@GetMapping("/shipmentReport")
	public String shipmentReport() {
		return "shipmentReport";
	}
	
	@GetMapping("/accountAdd")
	public String accountAdd() {
		return "accountAdd";
	}
	
	@GetMapping("/accountMgmt")
	public String accountMgmt() {
		return "accountMgmt";
	}
	
	
	

}
