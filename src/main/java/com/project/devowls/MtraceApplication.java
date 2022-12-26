package com.project.devowls;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.devowls.job.TestJob;

@SpringBootApplication
public class MtraceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MtraceApplication.class, args);
		
		
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            
            JobDetail job = newJob(TestJob.class)
                .withIdentity("jobName", Scheduler.DEFAULT_GROUP)
                .build();
            
            Trigger trigger = newTrigger()
                .withIdentity("trggerName", Scheduler.DEFAULT_GROUP)
                .withSchedule(cronSchedule("5 * * * * ?"))    //초 분 시 일 월 요일 년도
                //.withSchedule(cronSchedule("* * 7 * * ?"))    //초 분 시 일 월 요일 년도
                .build();
                        
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch(Exception e) {
            e.printStackTrace();
        }        
	}

}
