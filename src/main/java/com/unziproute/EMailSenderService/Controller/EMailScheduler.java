package com.unziproute.EMailSenderService.Controller;

import com.unziproute.EMailSenderService.Service.EMailSchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EMailScheduler {

    @Autowired
    private EMailSchedulerService eMailSchedulerService;

    @Value("${cronValue}")
    private String cronExpression;

    @Scheduled(cron = "${cronValue}", zone = "Asia/Kolkata")
    public void sendScheduledEmail() {
        eMailSchedulerService.sendEmail("to@gmail.com", "Scheduled Email", "This is a scheduled email.");
    }
}
