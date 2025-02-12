package com.example.tododevelop.service;

import com.example.tododevelop.dto.*;

import java.util.List;

public interface UserService {
    void regist(RegistUserRequestDto dto);

    UserResponseDto view(Long id);

    List<UserResponseDto> viewAll(AllUserRequestDto dto);

    void updateUser(UpdateUserDto dto, UserResponseDto sessionData);

    void deleteUser(Long id, UserResponseDto sessionData);

    UserResponseDto login(LoginRequestDto dto);
}
