package com.example.tododevelop.service;

import com.example.tododevelop.dto.*;
import com.example.tododevelop.entity.Comments;
import com.example.tododevelop.entity.ToDo;
import com.example.tododevelop.entity.User;
import com.example.tododevelop.repository.BoardRepository;
import com.example.tododevelop.repository.CommentRepository;
import com.example.tododevelop.repository.UserRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void regist(RegistCommentRequestDto dto, UserResponseDto sessionData) {

        ToDo toDo = boardRepository.findByIdAndUserIdOrElseThrow(dto.getId(), sessionData.getId());

        Comments comment = new Comments(dto.getContents(), toDo);

        commentRepository.save(comment);
    }

    @Override
    public CommentResponseDto view(Long id) {

        if (verify(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong range id");
        }

        Comments comment = commentRepository.findByIdOrElseThrow(id);

        CommentResponseDto dto = new CommentResponseDto(comment);

        return dto;
    }

    @Override
    public List<CommentResponseDto> viewAll() {
        return commentRepository.findAll().stream().map(CommentResponseDto::new).toList();
    }

    @Override
    @Transactional
    public void updateComment(UpdateCommentRequestDto dto, UserResponseDto sessionData) {

        Comments comment = commentRepository.findByIdAndUserIdOrElseThrow(dto.getId(), sessionData.getId());

        comment.updateComment(dto.getContents());
    }

    @Override
    @Transactional
    public void deleteComment(Long id, UserResponseDto sessionData) {

        if (verify(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong range id");
        }

        Comments comment = commentRepository.findByIdAndUserIdOrElseThrow(id, sessionData.getId());

        comment.deleteComment();
    }

    public boolean verify(Long id) {
        if (id < 1 || id > 500) {
            return true;
        }
        return false;
    }
}
