package com.unziproute.EMailSenderService.Controller;

import com.unziproute.EMailSenderService.Service.EMailService;
import com.unziproute.EMailSenderService.Vo.CustomResponse;
import com.unziproute.EMailSenderService.Vo.EMailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/email")
public class EmailSenderController {

    @Autowired
    private EMailService eMailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EMailRequest request) {
        eMailService.sendEMailWithHtml(request.getTo(), request.getSubject(), request.getMessage());
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .message("Email send successfully")
                        .httpStatus(String.valueOf(HttpStatus.OK))
                        .success(true)
                        .build()
        );
    }

    @PostMapping("/send-with-file")
    public ResponseEntity<CustomResponse> sendEMailWithFile(@RequestPart EMailRequest request, @RequestPart MultipartFile file) throws IOException {
        eMailService.sendEMailWithInputStream(request.getTo(), request.getSubject(), request.getMessage(), file.getInputStream());
        return ResponseEntity.ok(
                CustomResponse.builder()
                        .message("Email send successfully")
                        .httpStatus(String.valueOf(HttpStatus.OK))
                        .success(true)
                        .build()
        );
    }

}
