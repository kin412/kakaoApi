package com.kim.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class mailSend {
	
	public void mailSendProp(String addressee, String title, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("asd123@gmail.com"/*"발신자 메일 주소"*/,"asdasdqwe" /*"앱 비밀번호 - 인증없이 로그인 가능"*/);
			}
		});
		
		String real_addressee = addressee; // 수신자 메일 주소
		String real_title = title;
		String real_content = "<h2 style='color:blue'>"+content+"</h2>";
		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("asd123@gmail.com"/*"발신자 메일 주소"*/, "관리자"/*표시될 이름*/, "utf-8"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(real_addressee));
			message.setSubject(real_title);
			message.setContent(real_content, "text/html; charset=utf-8");

			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
