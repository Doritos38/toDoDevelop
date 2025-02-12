package com.example.tododevelop.service;


import com.example.tododevelop.dto.RegistToDoRequestDto;
import com.example.tododevelop.dto.ResponseDto;
import com.example.tododevelop.dto.UpdateToDoRequestDto;

import java.util.List;


public interface BoardService {


    void regist(RegistToDoRequestDto dto);

    ResponseDto view(Long id);

    List<ResponseDto> allView(String userName, String title);

    void updateToDo(UpdateToDoRequestDto updateToDoDto);

    void deleteToDo(Long id);
}
