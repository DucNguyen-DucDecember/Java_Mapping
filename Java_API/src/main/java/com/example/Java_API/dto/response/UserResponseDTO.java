package com.example.Java_API.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private int age;
    private String cccd;
}
