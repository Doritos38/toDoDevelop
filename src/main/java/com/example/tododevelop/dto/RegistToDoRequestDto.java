package com.example.tododevelop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistToDoRequestDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,50}$")
    private final String title;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\s!@#$%^&*()_-]{1,500}$")
    private final String contents;
}
