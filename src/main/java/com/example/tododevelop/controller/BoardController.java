package com.example.tododevelop.controller;


import com.example.tododevelop.Const;
import com.example.tododevelop.dto.*;
import com.example.tododevelop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<ToDoResponseDto> registToDo(@ModelAttribute RegistToDoRequestDto dto
            , @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto sessionData) {

        boardService.regist(dto, sessionData);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> viewToDo(@PathVariable Long id) {

        return new ResponseEntity<>(boardService.view(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ToDoResponseDto>> viewAllToDo(@ModelAttribute AllToDoRequestDto dto) {

        return new ResponseEntity<>(boardService.viewAll(dto), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ToDoResponseDto> updateToDo(@RequestBody UpdateToDoRequestDto dto
            , @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto sessionData) {

        boardService.updateToDo(dto, sessionData);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoResponseDto> deleteToDo(@PathVariable Long id
            , @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto sessionData) {

        boardService.deleteToDo(id, sessionData);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}