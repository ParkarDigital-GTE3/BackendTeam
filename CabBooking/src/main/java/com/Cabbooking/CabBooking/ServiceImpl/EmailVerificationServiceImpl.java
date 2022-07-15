package com.Cabbooking.CabBooking.ServiceImpl;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.Cabbooking.CabBooking.Service.EmailVerificationService;

@Service
public class EmailVerificationServiceImpl implements EmailVerificationService {


	public boolean sendEmail(String subject,String message,String to) {
		boolean f = false;
		String from = "Pranavbharambe7@gmail.com";
		
		//Variable for gmail
		String host = "smtp.gmail.com";
		
		//get system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES"+properties);
		
		//setting important information to properties object
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", from);
		properties.put("mail.smtp.password", "rjuqotdpvnhtayzr");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//get Session object
		Session session = Session.getInstance(properties, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				System.out.println("in password authen");
				return new PasswordAuthentication("Pranavbharambe7@gmail.com","rjuqotdpvnhtayzr");
				
			}
		});
		System.out.println("out password auth");
		session.setDebug(true);
		
		//Step 2 : compose the message [text,multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
		
		//from email
		m.setFrom(from);
		
		//adding recipient to message
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		//adding subject to message
		m.setSubject(subject);
	
		
		//adding Text to message.
		m.setText(message);
		
		
		//Step 3 : send the message using Transport class
		Transport.send(m);
		f = true;
		
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return f;
		
	}
}
