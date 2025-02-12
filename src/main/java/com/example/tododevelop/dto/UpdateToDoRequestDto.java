package com.example.tododevelop.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UpdateToDoRequestDto {

    @NotNull
    @Min(value = 1)
    @Max(value = 500)
    private long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,50}$")
    private String title;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z가-힣0-9\\s!@#$%^&*()_-]{1,500}$") // 시간 남으면 내용이 없거나 제목이 없을때 대비해서 수정하기
    private String contents;
}
