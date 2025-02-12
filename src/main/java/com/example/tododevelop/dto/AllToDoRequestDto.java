package com.example.tododevelop.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AllToDoRequestDto {

    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$")
    private String userName;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$")
    private String title;

    @Pattern(regexp = "^(?!.*\\s)\\d{4}-\\d{2}-\\d{2}$")
    private String date;
}