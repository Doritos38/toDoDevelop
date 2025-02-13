package com.example.tododevelop.controller;

import com.example.tododevelop.config.Const;
import com.example.tododevelop.dto.*;
import com.example.tododevelop.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> registComment(@ModelAttribute @Valid RegistCommentRequestDto dto
            , @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto sessionData) {

        commentService.regist(dto, sessionData);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> viewComment(@PathVariable Long id) {

        return new ResponseEntity<>(commentService.view(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> viewAllComments() {

        return new ResponseEntity<>(commentService.viewAll(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<CommentResponseDto> updateComment(@RequestBody @Valid UpdateCommentRequestDto dto
            , @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto sessionData) {

        commentService.updateComment(dto, sessionData);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentResponseDto> deleteComment(@PathVariable Long id
            , @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto sessionData) {

        commentService.deleteComment(id, sessionData);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
