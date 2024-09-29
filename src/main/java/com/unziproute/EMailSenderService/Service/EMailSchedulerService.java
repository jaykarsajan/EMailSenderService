package com.unziproute.EMailSenderService.Service;

public interface EMailSchedulerService {

    void sendEmail(String to, String subject, String body);
}
