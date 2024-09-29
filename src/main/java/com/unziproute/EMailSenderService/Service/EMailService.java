package com.unziproute.EMailSenderService.Service;


import java.io.File;
import java.io.InputStream;

public interface EMailService {

    //send mail to single user
    void sendEMail(String to, String subject, String message);

    //send mail to multiple user
    void sendEMail(String[] to, String subject, String message);

    //send mail with html
    void sendEMailWithHtml(String to, String subject, String htmlContent);

    //send mail with file
    void sendEMailWithFile(String to, String subject, String message, File file);

    void sendEMailWithInputStream(String to, String subject, String message, InputStream is);

}
