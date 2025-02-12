package com.example.tododevelop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllUserRequestDto {

    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$")
    private final String userName;

    @Email
    @Size(max = 50)
    private final String email;

    @Pattern(regexp = "^(?!.*\\s)\\d{4}-\\d{2}-\\d{2}$")
    private final String date;
}