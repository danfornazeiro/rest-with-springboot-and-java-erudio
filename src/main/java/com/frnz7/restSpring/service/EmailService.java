package com.frnz7.restSpring.service;

import com.frnz7.restSpring.config.EmailConfig;
import com.frnz7.restSpring.mail.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final EmailSender emailSender;
    private final EmailConfig emailConfig;

    public EmailService(EmailSender emailSender, EmailConfig emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    public void sendSimpleEmail(String to, String subject, String body){
        emailSender
                .To(to)
                .withSubject(subject)
                .withMessage(body)
                .send(emailConfig);
    }

}
