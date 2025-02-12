package com.example.tododevelop.controller;

import com.example.tododevelop.dto.*;
import com.example.tododevelop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ToDoResponseDto> registUser (@ModelAttribute RegistUserRequestDto dto){

        userService.regist(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> viewUser (@PathVariable Long id){

        return new ResponseEntity<>(userService.view(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> viewAllUser(@ModelAttribute AllUserRequestDto dto){

        return new ResponseEntity<>(userService.viewAll(dto), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UpdateUserDto dto){

        userService.updateToDo(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> deleteUser (@PathVariable Long id){

        userService.deleteToDo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
