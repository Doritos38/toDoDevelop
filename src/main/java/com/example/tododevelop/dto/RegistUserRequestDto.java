package com.example.tododevelop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegistUserRequestDto {

    private String userName;

    private String email;
}
