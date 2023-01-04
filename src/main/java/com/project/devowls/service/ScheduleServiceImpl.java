package com.project.devowls.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.devowls.dao.ScheduleDAO;
import com.project.devowls.vo.EggTradeVO;
import com.project.devowls.vo.MessageVO;
import com.project.devowls.vo.ScheduleVO;

@Service
public class ScheduleServiceImpl implements ScheduleService {
	
	@Autowired
	ScheduleDAO sDAO;

	@Override
	public ArrayList<ScheduleVO> searchAccountListForSchedule() {
		// TODO Auto-generated method stub
		return sDAO.selectAccountListForSchedule();
	}

	@Override
	public ScheduleVO searchEggInfoForSchedule() {
		// TODO Auto-generated method stub
		return sDAO.selectEggInfoForSchedule();
	}

	@Override
	public String searchScheduleTerm(String jobName) {
		// TODO Auto-generated method stub
		return sDAO.selectScheduleTerm(jobName);
	}

	@Override
	public String searchTriggerName(String jobName) {
		// TODO Auto-generated method stub
		return sDAO.selectTriggerName(jobName);
	}

	@Override
	public ScheduleVO searchScheduleInfo() {
		// TODO Auto-generated method stub
		return sDAO.selectScheduleInfo();
	}

	@Override
	public ArrayList<ScheduleVO> searchScheduleAccount() {
		// TODO Auto-generated method stub
		return sDAO.selectScheduleAccount();
	}

	@Override
	public ArrayList<EggTradeVO> searchTradeInfoByHistNo() {
		// TODO Auto-generated method stub
		return sDAO.selectTradeInfoByHistNo();
	}

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void sendMail(MessageVO message) {
		
		
        
		
		SimpleMailMessage mail = new SimpleMailMessage();
		
		String subject = "[축산물 이력제 신고 내역] : " + message.getReportDate() + ", " + message.getTotalEgg() +"개 ";
		
		String template ="";
		System.out.println(" [메일 발송용] ====================================================== " );
//		System.out.println(""+ message.getHistNo());
//		
//		System.out.println("" + message.getHistNoResultCode());
//		System.out.println("" + message.getPackingResultCode());
//		System.out.println("" + message.getReportDate());
//		System.out.println("" + message.getSpawningDate());
//		System.out.println("" + message.getTotalEgg());
//		System.out.println("" + message.getTradeResultCode());
//		System.out.println("" + message.getTransInfo());
		
		template = message.getReportDate() + " 축산물이력제 신고 내역 \n"
				 + "\n"
				 + "산란일자 : " +message.getSpawningDate() +"\n"
				 + "이력번호 : " +message.getHistNo() +"\n"
				 + "신고수량 : " +message.getTotalEgg() +" 개\n"
				 + "\n"
				 + "이력번호 결과코드 : " + message.getHistNoResultCode() +"\n"
				 + "선별포장 결과코드 : " + message.getPackingResultCode() +"\n"
				 + "출고신고 결과코드 : " + message.getTradeResultCode() +"\n"
				 + "\n"
				 + "[거래처별 출고내역]\n"
				 + message.getTransInfo();
		
		System.out.println(" [메일 발송용] \n " +  template);

	
		mail.setSubject(subject);
		mail.setText(template);

		mail.setTo("hik2075@daum.net");
		mail.setCc("llallallall@kakao.com");

		mail.setFrom("devowls@naver.com");
		mailSender.send(mail);
		
	}

}
