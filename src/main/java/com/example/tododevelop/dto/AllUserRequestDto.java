package com.example.tododevelop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AllUserRequestDto {

    private String userName;

    private String email;

    private String date;
}
