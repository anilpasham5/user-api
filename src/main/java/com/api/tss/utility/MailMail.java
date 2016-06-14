 package com.api.tss.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailMail {

private MailSender mailSender;
public void setMailSender(MailSender mailSender){
	this.mailSender=mailSender;	
}
//working fine
public void sendMail(String to,String from,String subject,String msg){
	SimpleMailMessage message=new SimpleMailMessage();
	message.setTo(to);
	message.setFrom(from);
	message.setSubject(subject);
	message.setText(msg);
	mailSender.send(message);
}
//working fine
public static void test(){
    JavaMailSenderImpl javaMailSenderImpl=new JavaMailSenderImpl();
    javaMailSenderImpl.setHost("smtp.gmail.com");
    javaMailSenderImpl.setUsername("tsshyd140@gmail.com");
    javaMailSenderImpl.setPassword("!23Admin");
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.auth", true);
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.port", 465);
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.port", 465);
    SimpleMailMessage message=new SimpleMailMessage(); 
	message.setTo("aniltechbion@gmail.com");
	message.setFrom("tsshyd140@gmail.com");
	message.setSubject("Activate Account");
	message.setText("Hello World!.......");
	javaMailSenderImpl.send(message);
}

public static void main(String[] args) {
	test();
	//mailThroughPrperties("aniltechbion@gmail.com","Test","Mail Success");
}
//working fine
public static void mailThroughPrperties(String toMail,String mailSubject,String mailText){
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
	System.out.println(properties.get("username"));
	JavaMailSenderImpl javaMailSenderImpl=new JavaMailSenderImpl();
	javaMailSenderImpl.setHost(properties.getProperty("host"));
	javaMailSenderImpl.setUsername(properties.getProperty("username"));
	javaMailSenderImpl.setPassword(properties.getProperty("password"));
	javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.auth", Boolean.parseBoolean(properties.getProperty("mailsmtpauth")));
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.port", Integer.parseInt(properties.getProperty("mailsmtpsocketFactoryport")));
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.class",properties.get("mailsmtpsocketFactoryclass"));
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.port", Integer.parseInt(properties.getProperty("mailsmtpport")));
    //props.put("mail.smtp.socketFactory.fallback", "true");
    javaMailSenderImpl.getJavaMailProperties().put("mail.smtp.socketFactory.fallback", "true");
    SimpleMailMessage message=new SimpleMailMessage(); 
   	message.setTo(toMail);
   	message.setFrom(properties.getProperty("username"));
   	message.setSubject(mailSubject);
   	message.setText(mailText);
   	javaMailSenderImpl.send(message);
}

}
