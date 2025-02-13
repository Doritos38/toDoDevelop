package com.example.tododevelop.service;


import com.example.tododevelop.dto.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.util.List;


public interface BoardService {


    void regist(RegistToDoRequestDto dto, UserResponseDto sessionData);

    ToDoResponseDto view(Long id);

    List<ToDoResponseDto> viewAll(AllToDoRequestDto dto);

    void updateToDo(UpdateToDoRequestDto dto, UserResponseDto sessionData);

    void deleteToDo(Long id, UserResponseDto sessionData);

    Page<ToDoPageResponseDto> pagingToDo(@NotNull @Min(1) int page, @NotNull @Min(1) int size);
}