package com.example.tododevelop.service;

import com.example.tododevelop.dto.*;

import java.util.List;

public interface UserService {
    void regist(RegistUserRequestDto dto);

    UserResponseDto view(Long id);

    List<UserResponseDto> viewAll(AllUserRequestDto dto);

    void updateUser(UpdateUserDto dto);

    void deleteUser(Long id);

    UserResponseDto login(LoginRequestDto dto);
}
