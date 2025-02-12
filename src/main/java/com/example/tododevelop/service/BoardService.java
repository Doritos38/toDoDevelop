package com.example.tododevelop.service;


import com.example.tododevelop.dto.AllToDoRequestDto;
import com.example.tododevelop.dto.RegistToDoRequestDto;
import com.example.tododevelop.dto.ToDoResponseDto;
import com.example.tododevelop.dto.UpdateToDoRequestDto;

import java.util.List;


public interface BoardService {


    void regist(RegistToDoRequestDto dto);

    ToDoResponseDto view(Long id);

    List<ToDoResponseDto> viewAll(AllToDoRequestDto dto);

    void updateToDo(UpdateToDoRequestDto updateToDoDto);

    void deleteToDo(Long id);
}