package com.example.tododevelop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistCommentRequestDto {

    @NotNull
    @Min(value = 1)
    @Max(value = 500)
    private final Long id;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\s!@#$%^&*()_-]{1,500}$")
    private final String contents;

}
