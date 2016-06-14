package com.api.tss.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Generator {
    //working fine
	public static String generateToken(){
		String string=UUID.randomUUID().toString();
		System.out.println(string);
		return string;
	}
	public static void main(String[] args) {
		sendEmail("bhanuteja0289@gmail.com","Hey","Hii");
	}
	//working fine
	public static void generateMail2(String activationLink,String emailId) {
		final String username = "tsshyd140@gmail.com";
		final String password = "!23Admin";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
        props.put("mail.debug", "true");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("aniltechbion@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailId));
			message.setSubject("Activate Account");
			message.setText("You can activate your account by  "
					+activationLink+">Clicking Here");
   			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
   //working fine
   public static void sendEmail2(String toMail,String mailSubject,String mailText){
		Properties properties=new Properties();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File("/home/techbion/git/user-api/src/main/resources/mail.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JavaMailSenderImpl javaMailSenderImpl=new JavaMailSenderImpl();
		javaMailSenderImpl.setHost(properties.getProperty("host"));
		javaMailSenderImpl.setUsername(properties.getProperty("username"));
		javaMailSenderImpl.setPassword(properties.getProperty("password"));
		javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.auth", Boolean.parseBoolean(properties.getProperty("mailsmtpauth")));
	    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.port", Integer.parseInt(properties.getProperty("mailsmtpsocketFactoryport")));
	    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.class",properties.get("mailsmtpsocketFactoryclass"));
	    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.port", Integer.parseInt(properties.getProperty("mailsmtpport")));
	    //props.put("mail.smtp.socketFactory.fallback", "true");
	   javaMailSenderImpl.getJavaMailProperties().put("mail.debug", "true");
	    SimpleMailMessage message=new SimpleMailMessage(); 
	   	message.setTo(toMail);
	   	message.setFrom(properties.getProperty("username"));
	   	message.setSubject(mailSubject);
	   	message.setText(mailText);
	   	javaMailSenderImpl.send(message);
	   	System.out.println("Done");
	}
   //working fine
   public static void sendEmail(String toMail,String mailSubject,String mailText){
	
	Properties properties=new Properties();
	FileInputStream fileInputStream = null;
	try {
		fileInputStream = new FileInputStream(new File("/home/techbion/workspace/User-API/src/main/resources/mail.properties"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		properties.load(fileInputStream);
	} catch (IOException e) {
		e.printStackTrace();
	}
	JavaMailSenderImpl javaMailSenderImpl=new JavaMailSenderImpl();
	javaMailSenderImpl.setHost(properties.getProperty("host"));
	javaMailSenderImpl.setUsername(properties.getProperty("username"));
	javaMailSenderImpl.setPassword(properties.getProperty("password"));
	javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.auth", Boolean.parseBoolean(properties.getProperty("mailsmtpauth")));
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.port", Integer.parseInt(properties.getProperty("mailsmtpsocketFactoryport")));
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.class",properties.get("mailsmtpsocketFactoryclass"));
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.port", Integer.parseInt(properties.getProperty("mailsmtpport")));
    //props.put("mail.smtp.socketFactory.fallback", "true");
    javaMailSenderImpl.getJavaMailProperties().put("mail.debug", "true");
    SimpleMailMessage message=new SimpleMailMessage(); 
   	message.setTo(toMail);
   	message.setFrom(properties.getProperty("username"));
   	message.setSubject(mailSubject);
   	message.setText(mailText);
   	javaMailSenderImpl.send(message);
   	System.out.println("Done");
}

}
