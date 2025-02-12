package com.example.tododevelop.service;

import com.example.tododevelop.dto.*;

import java.util.List;

public interface UserService {
    void regist(RegistUserRequestDto dto);

    UserResponseDto view(Long id);

    List<UserResponseDto> viewAll(AllUserRequestDto dto);

    void updateToDo(UpdateUserDto dto);

    void deleteToDo(Long id);
}
