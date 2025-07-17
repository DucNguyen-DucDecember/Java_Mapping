package com.example.Java_API.dto.resquest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRequestDTO {
    @JsonProperty(value = "user_name")
    private String name;
    private String email;
    private int age;
    private String cccd;
}

