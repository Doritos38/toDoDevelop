package com.example.tododevelop.controller;

import com.example.tododevelop.config.Const;
import com.example.tododevelop.dto.*;
import com.example.tododevelop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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

    @PostMapping("/regi")
    public ResponseEntity<ToDoResponseDto> registUser(@ModelAttribute @Valid RegistUserRequestDto dto) {

        userService.regist(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> viewUser(@PathVariable Long id) {

        return new ResponseEntity<>(userService.view(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> viewAllUser(@ModelAttribute @Valid AllUserRequestDto dto) {

        return new ResponseEntity<>(userService.viewAll(dto), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody @Valid UpdateUserRequestDto dto
            , @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto sessionData) {

        userService.updateUser(dto, sessionData);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> deleteUser(@PathVariable Long id
            , @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto sessionData) {

        userService.deleteUser(id, sessionData);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@ModelAttribute @Valid LoginRequestDto dto, HttpServletRequest request) {

        UserResponseDto user = userService.login(dto);

        HttpSession session = request.getSession();

        session.setAttribute(Const.LOGIN_USER, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserResponseDto> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
