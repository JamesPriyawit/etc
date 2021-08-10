package com.example.etc.services;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private VelocityEngine velocityEngine;

    @Value("${mail.smtp.user}")
    private String sender;

    public void sendEmail(
            String templateName,
            String subject,
            Map<String, Object> variables,
            List<String> receivers,
            String cc,
            Map<String, Object> attachment
    ) {
        Assert.notEmpty(receivers);
        try {
            List<MimeMessage> mimeMessages = new ArrayList<>();
            for (String email : receivers) {
                boolean isEmail = true;
                try {
                    InternetAddress emailAddr = new InternetAddress(email);
                    emailAddr.validate();
                } catch (AddressException ex) {
                    isEmail = false;
                } catch (Exception ex) {
                    isEmail = false;
                }
                if (!isEmail)
                    continue;
                final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
                final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                message.setSubject(subject);
                message.setFrom(sender);
                message.setTo(email);
                if(cc != null) {
                    message.setCc(cc);
                }
                if (attachment != null) {
                    message.addAttachment((String) attachment.get("type") + (String) attachment.get("fileExtension"), new File((String) attachment.get("filePath")));
                }
                //prepare html content use velocity
                String htmlText = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "templates/" + templateName, "UTF-8", variables);
                message.setText(htmlText, true); // true = isHtml
                mimeMessages.add(mimeMessage);
            }
            MimeMessage[] arr = mimeMessages.toArray(new MimeMessage[mimeMessages.size()]);
            this.javaMailSender.send(arr);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        } catch (MailAuthenticationException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
