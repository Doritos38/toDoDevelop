package com.example.tododevelop.service;

import com.example.tododevelop.dto.*;
import jakarta.validation.Valid;

import java.util.List;

public interface CommentService {
    void regist(@Valid RegistCommentRequestDto dto, UserResponseDto sessionData);

    CommentResponseDto view(Long id);

    List<CommentResponseDto> viewAll();

    void updateComment(@Valid UpdateCommentRequestDto dto, UserResponseDto sessionData);

    void deleteComment(Long id, UserResponseDto sessionData);
}
