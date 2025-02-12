package com.example.tododevelop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String email;

    private boolean deleted = false;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public void updateUser(String userName, String email){
        this.userName = userName;
        this.email = email;
    }

    public void deleteUser(){
        this.deleted = true;
    }
}
