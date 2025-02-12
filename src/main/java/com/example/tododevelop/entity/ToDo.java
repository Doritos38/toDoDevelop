package com.example.tododevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "todo")
@NoArgsConstructor
public class ToDo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void updateToDo(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public void deleteToDo(){
        this.deleted = true;
    }

    public ToDo(String title, String contents, User user){
        this.user = user;
        this.title = title;
        this.contents = contents;
    }


}
