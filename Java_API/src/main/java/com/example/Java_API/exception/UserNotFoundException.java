package com.example.Java_API.exception;

import com.example.Java_API.response.ErrorDomain;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String message;
    private final String errorCode = "user";
    private final ErrorDomain domain = ErrorDomain.NOT_FOUND;
}
