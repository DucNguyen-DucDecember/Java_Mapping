package com.example.Java_API.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Objects;

@Data
@Builder
public class SuccessResponse<T> {
    private final OperationType operationType = OperationType.SUCCESS;
    private final String message = "Success";
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private T content;
    private int statusCode;
    private int size;
    private int page;

    public static <T> SuccessResponse<T> of(final T content){
        return SuccessResponse.<T>builder()
                .content(content)
                .statusCode(HttpStatus.OK.value())
                .size(getSize(content))
                .build();
    }

    public static <T> int getSize(T content){
        if(Objects.nonNull(content) && content instanceof Collection<?>){
            return ((Collection<?>) content).size();
        }
        return 0;
    }
}
