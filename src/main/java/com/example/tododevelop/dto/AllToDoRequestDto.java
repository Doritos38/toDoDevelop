package com.example.tododevelop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class AllToDoRequestDto {

    private String userName;

    private String title;

    private String date;
}
