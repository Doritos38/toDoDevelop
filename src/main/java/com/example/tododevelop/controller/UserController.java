package com.example.tododevelop.controller;

import com.example.tododevelop.Const;
import com.example.tododevelop.dto.*;
import com.example.tododevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

        userService.updateUser(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> deleteUser (@PathVariable Long id){

        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@ModelAttribute LoginRequestDto dto, HttpServletRequest request){

        UserResponseDto user = userService.login(dto);

        HttpSession session = request.getSession();

        session.setAttribute(Const.LOGIN_USER, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserResponseDto> logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
