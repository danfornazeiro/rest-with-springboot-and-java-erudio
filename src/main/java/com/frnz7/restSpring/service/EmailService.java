package com.frnz7.restSpring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.frnz7.restSpring.config.EmailConfig;
import com.frnz7.restSpring.data.dto.request.EmailRequestDTO;
import com.frnz7.restSpring.mail.EmailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

@Service
public class EmailService {

    private final EmailSender emailSender;
    private final EmailConfig emailConfig;

    public EmailService(EmailSender emailSender, EmailConfig emailConfig) {
        this.emailSender = emailSender;
        this.emailConfig = emailConfig;
    }

    public void sendSimpleEmail(EmailRequestDTO emailRequestDTO){
        emailSender
                .To(emailRequestDTO.getTo())
                .withSubject(emailRequestDTO.getSubject())
                .withMessage(emailRequestDTO.getBody())
                .send(emailConfig);
    }

    public void setEmailWithAttachment(String emailRequestJson, MultipartFile attachment){
        File tempFile = null;
        try {
            EmailRequestDTO emailRequestDTO = new ObjectMapper().readValue(emailRequestJson, EmailRequestDTO.class);
            tempFile = File.createTempFile("attachment", attachment.getOriginalFilename());
            attachment.transferTo(tempFile);

            emailSender
                    .To(emailRequestDTO.getTo())
                    .withSubject(emailRequestDTO.getSubject())
                    .withMessage(emailRequestDTO.getBody())
                    .attach(tempFile.getAbsolutePath())
                    .send(emailConfig);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing email request", e);
        }catch (IOException e) {
            throw new RuntimeException("Error processing the attachment", e);
        } finally {
            if(tempFile != null && tempFile.exists()){
                tempFile.delete();
            }
        }
    }

}
