package com.example.tododevelop.dto;

import com.example.tododevelop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class UserResponseDto {

    private long id;

    private String userName;

    private String email;

    private LocalDate date;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.email = user.getEmail();
        this.date = user.getDate().toLocalDate();
    }
}
