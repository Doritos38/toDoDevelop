package com.example.tododevelop.dto;

import com.example.tododevelop.entity.Comments;
import com.example.tododevelop.entity.ToDo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ToDoResponseDto {

    private long id;

    private String userName;

    private String title;

    private String contents;

    private Long userId;

    private List<String> comments;

    private LocalDate date;

    public ToDoResponseDto(ToDo toDo) {
        this.id = toDo.getId();
        this.userName = toDo.getUser().getUserName();
        this.userId = toDo.getUser().getId();
        this.title = toDo.getTitle();
        this.contents = toDo.getContents();
        this.date = toDo.getDate().toLocalDate();
        this.comments = toDo.getComments().stream().map(Comments::getContents).collect(Collectors.toList());
    }
}
