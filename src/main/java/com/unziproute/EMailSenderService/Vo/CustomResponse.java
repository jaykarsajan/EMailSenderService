package com.unziproute.EMailSenderService.Vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomResponse {

    private String message;
    private String httpStatus;
    private Boolean success = false;
}
