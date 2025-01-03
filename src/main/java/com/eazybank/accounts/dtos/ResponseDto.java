package com.eazybank.accounts.dtos;

import lombok.*;


@NoArgsConstructor
@Getter
@Setter
public class ResponseDto {
    private String statusCode;
    private String statusMessage;

    public ResponseDto(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }


}
