package com.ensa.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
   private final JavaMailSender mailSender;

    public void sendEmail(SendEmailRequest sendEmailRequest){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mariam.agdad40@gmail.com");
        message.setTo(sendEmailRequest.getToEmail());
        message.setSubject(sendEmailRequest.getSubject());
        message.setText(sendEmailRequest.getBody());
        mailSender.send(message);
    }
}
