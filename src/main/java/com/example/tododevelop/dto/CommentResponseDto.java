package com.example.tododevelop.dto;

import com.example.tododevelop.entity.Comments;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private Long id;

    private String contents;

    private Long userId;

    private Long toDoId;

    private LocalDate date;

    public CommentResponseDto(Comments comments){
        this.id = comments.getId();
        this.contents = comments.getContents();
        this.userId = comments.getToDo().getUser().getId();
        this.toDoId = comments.getToDo().getId();
        this.date = comments.getDate().toLocalDate();
    }

}
