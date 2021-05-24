package com.alibaba.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Properties;

@Service
public class MailService {
    @Resource
    private JavaMailSender mailSender;

    // 从配置文件中注入发件人的姓名
    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSimpleMail(String to, String subject, String content) {

        Properties prop = new Properties();
        prop.setProperty("mail.smtp.port", "465");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");

        prop.setProperty("mail.smtp.socketFactory.port", "465");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail); // 发件人
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        System.out.println(fromEmail);
        System.out.println(to);
        System.out.println(subject);
        System.out.println(content);
        mailSender.send(message);
    }
}