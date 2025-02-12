package com.example.tododevelop.dto;

import com.example.tododevelop.entity.ToDo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ResponseDto {

    private long id;

    private String userName;

    private String title;

    private String contents;

    private Long userId;

    private LocalDate date;

    public ResponseDto (ToDo toDo){
        this.id = toDo.getId();
        this.userName = toDo.getUserName();
        this.title = toDo.getTitle();
        this.contents = toDo.getContents();
        this.date = toDo.getDate().toLocalDate();


    }
}
