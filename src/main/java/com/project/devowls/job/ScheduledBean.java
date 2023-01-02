package com.project.devowls.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.devowls.service.ScheduleService;


@Configuration
public class ScheduledBean {
	
	@Autowired
	ScheduleService sService;
	
	@Bean
	public String getMtraceSchedule() {
		
		String jobName = "mtraceAPI";
		String scheduleTerm = sService.searchScheduleTerm(jobName);
	    System.out.println(scheduleTerm);
	    return scheduleTerm;
	}
	
	@Bean
	public String getTestSchedule() {
		
		String jobName = "test";
		String scheduleTerm = sService.searchScheduleTerm(jobName);
	    System.out.println(scheduleTerm);
	    return scheduleTerm;
	}

}
