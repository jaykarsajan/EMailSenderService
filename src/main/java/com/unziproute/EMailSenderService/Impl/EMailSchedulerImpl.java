package com.unziproute.EMailSenderService.Impl;

import com.unziproute.EMailSenderService.Service.EMailSchedulerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EMailSchedulerImpl implements EMailSchedulerService {

    private Logger logger = LoggerFactory.getLogger(EMailSchedulerImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${sender.email.id.value}")
    String senderEmailId;

    @Override
    public void sendEmail(String to, String subject, String body) {
        logger.info("Inside EMailSchedulerImpl sendEMail");
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            message.setFrom(senderEmailId);
            mailSender.send(message);
            logger.info("Email has been sent");
        } catch (Exception e) {
            logger.info("Inside EMailSchedulerImpl sendEMail catch :: " + e.getMessage());
        }
    }
}
