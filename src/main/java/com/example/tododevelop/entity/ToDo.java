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

    @Column(name = "user_name")
    private String userName;

    private String title;

    private String contents;

    private boolean deleted;


    public void updateToDo(String title, String contents){
        this.title = title;
        this.contents = contents;
    }

    public void deleteToDo(){
        this.deleted = true;
    }

    public ToDo(String userName, String title, String contents){
        this.userName = userName;
        this.title = title;
        this.contents = contents;
    }


}
