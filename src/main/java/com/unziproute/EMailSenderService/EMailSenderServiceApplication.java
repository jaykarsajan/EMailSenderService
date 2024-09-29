package com.unziproute.EMailSenderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EMailSenderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EMailSenderServiceApplication.class, args);
        System.out.println("Run");
    }
}
