package com.example.tododevelop.service;


import com.example.tododevelop.dto.*;

import java.util.List;


public interface BoardService {


    void regist(RegistToDoRequestDto dto, UserResponseDto sessionData);

    ToDoResponseDto view(Long id);

    List<ToDoResponseDto> viewAll(AllToDoRequestDto dto);

    void updateToDo(UpdateToDoRequestDto dto, UserResponseDto sessionData);

    void deleteToDo(Long id, UserResponseDto sessionData);
}