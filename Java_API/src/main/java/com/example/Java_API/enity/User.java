package com.example.Java_API.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "name should not be blank")
    private  String name;

    @Column(name = "col_email")
    private String email;

    @NotNull
    @Min(message = "Should not be younger than 18", value = 18l)
    @Max(message = "Should not be older than 100", value = 100l)
    private int age;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @JsonIgnore
    private Profile profile;
}
