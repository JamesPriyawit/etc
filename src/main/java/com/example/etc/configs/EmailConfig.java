package com.example.etc.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailConfig {
    @Value("${mail.protocol}")
    private String protocol;
    @Value("${mail.smtp.host}")
    private String host;
    @Value("${mail.smtp.port}")
    private int port;
    @Value("${mail.smtp.auth}")
    private String auth;
    @Value("${mail.smtp.starttls.enable}")
    private String starttls;
    @Value("${mail.smtp.ssl.trust}")
    private String ssl;
    @Value("${mail.from}")
    private String from;
    @Value("${mail.from.name}")
    private String fromName;
    @Value("${mail.smtp.user}")
    private String username;
    @Value("${mail.smtp.password}")
    private String password;
    @Value("${mail.debug}")
    private String debug;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttls);
        mailProperties.put("mail.smtp.ssl.trust", ssl);
        mailProperties.put("mail.smtp.host", host);
        mailProperties.put("mail.from", from);

        mailProperties.put("mail.smtp.port", port);
        mailProperties.put("mail.mime.charset", "utf8");
        mailProperties.put("defaultEncoding", "UTF-8");

        mailProperties.setProperty("mail.debug", debug);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }
}
