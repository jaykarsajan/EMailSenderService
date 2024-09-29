package com.unziproute.EMailSenderService.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EMailRequest {

    private String to;
    private String subject;
    private String message;

}
