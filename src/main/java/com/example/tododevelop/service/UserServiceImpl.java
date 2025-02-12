package com.example.tododevelop.service;

import com.example.tododevelop.dto.*;
import com.example.tododevelop.entity.User;
import com.example.tododevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void regist(RegistUserRequestDto dto) {

        User user = new User(dto.getUserName(), dto.getEmail(), dto.getPassword());

        userRepository.save(user);
    }

    @Override
    public UserResponseDto view(Long id) {

        User user = userRepository.findByIdOrElseThrow(id);

        UserResponseDto userResponseDto = new UserResponseDto(user);


        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> viewAll(AllUserRequestDto dto) {

        LocalDate date;

        if (dto.getDate() == null) {
            date = null;
        } else {
            date = LocalDate.parse(dto.getDate());
        }

        List<User> allByConditions = userRepository.findAllByConditions(dto.getUserName(), dto.getEmail(), date);

        return allByConditions.stream().map(UserResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateToDo(UpdateUserDto dto) {

        User user = userRepository.findByIdOrElseThrow(dto.getId());

        user.updateUser(dto.getUserName(), dto.getEmail());
    }

    @Override
    @Transactional
    public void deleteToDo(Long id) {

        User user = userRepository.findByIdOrElseThrow(id);

        user.deleteUser();
    }
}