package com.unziproute.EMailSenderService.Impl;

import com.unziproute.EMailSenderService.Service.EMailService;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EMailServiceImpl implements EMailService {

    private Logger logger = LoggerFactory.getLogger(EMailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${sender.email.id.value}")
    String senderEmailId;

    @Override
    public void sendEMail(String to, String subject, String message) {
        logger.info("Inside EMailServiceImpl sendEMail");
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            simpleMailMessage.setFrom(senderEmailId);
            mailSender.send(simpleMailMessage);
            logger.info("Email has been sent");
        } catch (Exception e) {
            logger.info("Inside EMailServiceImpl sendEMail catch :: " + e.getMessage());
        }
    }

    @Override
    public void sendEMail(String[] to, String subject, String message) {
        try {
            logger.info("Inside EMailServiceImpl sendEMail");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            simpleMailMessage.setFrom(senderEmailId);
            logger.info("Email has been sent");
        } catch (Exception e) {
            logger.info("Inside EMailServiceImpl sendEMail catch :: " + e.getMessage());
        }
    }

    @Override
    public void sendEMailWithHtml(String to, String subject, String htmlContent) {
        logger.info("Inside EMailServiceImpl sendEMailWithHtml");
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(senderEmailId);
            helper.setText(htmlContent, true);
            mailSender.send(message);
            logger.info("Email has been sent");
        } catch (Exception e) {
            logger.info("Inside EMailServiceImpl sendEMailWithHtml catch :: " + e.getMessage());
        }
    }

    @Override
    public void sendEMailWithFile(String to, String subject, String message, File file) {
        logger.info("Inside EMailServiceImpl sendEMailWithFile");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message);
            helper.setFrom(senderEmailId);

            FileSystemResource fileSystemResource = new FileSystemResource(file);
            helper.addAttachment(fileSystemResource.getFilename(), file);
            mailSender.send(mimeMessage);
            logger.info("Email has been sent");
        } catch (Exception e) {
            logger.info("Inside EMailServiceImpl sendEMailWithInputStream catch :: " + e.getMessage());
        }
    }

    @Override
    public void sendEMailWithInputStream(String to, String subject, String message, InputStream is) {
        logger.info("Inside EMailServiceImpl sendEMailWithInputStream");
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(message,true);
            helper.setFrom(senderEmailId);

            File newFile = new File("src/main/resources/email/test.png");
            Files.copy(is, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(newFile);
            helper.addAttachment(fileSystemResource.getFilename(), newFile);
            mailSender.send(mimeMessage);
            logger.info("Email has been sent");
        } catch (Exception e) {
            logger.info("Inside EMailServiceImpl sendEMailWithInputStream catch :: " + e.getMessage());
        }
    }
}
