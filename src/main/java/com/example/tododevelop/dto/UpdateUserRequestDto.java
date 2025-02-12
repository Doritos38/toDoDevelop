package com.example.tododevelop.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateUserRequestDto {

    @NotNull
    @Min(value = 1)
    @Max(value = 500)
    private final long id;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$")
    private final String userName;

    @Email
    @Size(max = 50)
    @NotBlank
    private final String email;
}
