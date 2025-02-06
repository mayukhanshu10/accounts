package com.eazybank.accounts.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {
    /*
    apiPath: Which end Point path is being hit by client
    errorCode: What is the error code like 404,etc
    errorMessage: What is error message being produced
    errorTime: At what time error is encountered
     */
    private String apiPath;
    private HttpStatus errorCode;
    private String errorMessage;
    private LocalDateTime errorTime;

    public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
    }


}
