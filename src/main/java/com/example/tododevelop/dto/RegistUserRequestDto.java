package com.example.tododevelop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistUserRequestDto {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$")
    private final String userName;

    @Email
    @Size(max = 50)
    @NotBlank
    private final String email;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣!@#$%^&*(),.?\":{}|<>0-9]{1,20}$")
    private final String password;
}