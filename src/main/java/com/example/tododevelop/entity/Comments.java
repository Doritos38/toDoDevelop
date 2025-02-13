package com.example.tododevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comments extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @Column(name = "user_id")
    private Long userId;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "todo_id")
    private ToDo toDo;

    public void updateComment(String contents){
        this.contents = contents;
    }

    public void deleteComment(){
        this.deleted = true;
    }

    public Comments( String contents, ToDo toDo){
        this.userId = toDo.getUser().getId();
        this.contents = contents;
        this.toDo = toDo;
    }

}
