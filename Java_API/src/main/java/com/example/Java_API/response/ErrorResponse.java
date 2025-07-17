package com.example.Java_API.response;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private final OperationType operationType = OperationType.FAILURE;
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private String message;
    private int statusCode;
    private String errorCode;
    private ErrorDomain domain;
    private Map<String, Object> details;

    public static ErrorResponse of(final String message
            , final int statusCode
            , final String errorCode
            , final ErrorDomain domain){
        return ErrorResponse.builder()
                .message(message)
                .statusCode(statusCode)
                .errorCode(errorCode)
                .domain(domain)
                .build();
    }

    public static ErrorResponse of(final String message
            , final int statusCode
            , final String errorCode
            , final ErrorDomain domain
            , final Map<String, Object> errors){
        return ErrorResponse.builder()
                .message(message)
                .statusCode(statusCode)
                .errorCode(errorCode)
                .domain(domain)
                .details(errors)
                .build();
    }
}
