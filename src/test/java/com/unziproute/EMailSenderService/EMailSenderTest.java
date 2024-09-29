package com.unziproute.EMailSenderService;

import com.unziproute.EMailSenderService.Service.EMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
public class EMailSenderTest {

    @Autowired
    private EMailService eMailService;

    @Test
    void sendEMailTest() {
        eMailService.sendEMail("test@gmail.com", "Testing mail", "mail send using spring boot");
    }

    @Test
    void sendHtmlInEmail() {
        String html = "" +
                "<h1>Testing mail</h1>" +
                "";
        eMailService.sendEMailWithHtml("test@gmail.com", "Testing mail with html", html);
    }


    @Test
    void sendEmailWithFile() {

        eMailService.sendEMailWithFile(
                "test@gmail.com",
                "Testing mail with file",
                "mail send using spring boot with attach file",
                new File("F:\\Projects\\EMailSender\\EMailSenderService\\src\\main\\resources\\static\\Tiger.jpg")
        );
    }

    @Test
    void sendEmailWithInputStream() {
        File file = new File("F:\\Projects\\EMailSender\\EMailSenderService\\src\\main\\resources\\static\\Tiger.jpg");
        try {
            FileInputStream is = new FileInputStream(file);
            eMailService.sendEMailWithInputStream(
                    "test@gmail.com",
                    "Testing mail with file",
                    "mail send using spring boot with attach file",
                    is
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
