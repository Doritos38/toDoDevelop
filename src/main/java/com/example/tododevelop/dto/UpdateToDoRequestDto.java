package com.example.tododevelop.dto;

import lombok.Getter;

@Getter
public class UpdateToDoRequestDto {
    private long id;
    private String title;
    private String contents;
}
