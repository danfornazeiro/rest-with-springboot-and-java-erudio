package com.frnz7.restSpring.controller;

import com.frnz7.restSpring.controller.docs.EmailControllerDocs;
import com.frnz7.restSpring.data.dto.request.EmailRequestDTO;
import com.frnz7.restSpring.service.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/email/v1")
public class EmailController implements EmailControllerDocs {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestDTO emailRequestDTO) {
        service.sendSimpleEmail(emailRequestDTO);
        return new ResponseEntity<>("e-mail sent with success!", HttpStatus.OK);

    }

    @PostMapping(value = "/withAttachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Override
    public ResponseEntity<String> sendEmailWithAttachment(@RequestParam String emailRequestJson, @RequestParam MultipartFile attachment) {
        service.setEmailWithAttachment(emailRequestJson, attachment);
        return new ResponseEntity<>("E-mail with attachment sent with success!", HttpStatus.OK);
    }
}
