package com.example.tododevelop.dto;

import com.example.tododevelop.entity.ToDo;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ToDoPageResponseDto {
    private String title;
    private String contnets;
    private int conmmentCnt;
    private LocalDate createDate;
    private LocalDate updateDate;
    private String userName;

    public ToDoPageResponseDto(ToDo toDo) {
        this.contnets = toDo.getContents();
        this.title = toDo.getTitle();
        this.conmmentCnt = toDo.getComments().size();
        this.createDate = toDo.getDate().toLocalDate();
        this.updateDate = toDo.getUpdateDate().toLocalDate();
        this.userName = toDo.getUser().getUserName();
    }
}
