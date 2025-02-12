package com.example.tododevelop.service;

import com.example.tododevelop.dto.*;
import com.example.tododevelop.entity.User;
import com.example.tododevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

        isIdNull(id);

        User user = userRepository.findByIdOrElseThrow(id);

        UserResponseDto dto = new UserResponseDto(user);

        return dto;
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
    public void updateUser(UpdateUserRequestDto dto, UserResponseDto sessionData) {

        User user = userRepository.findByIdOrElseThrow(dto.getId());

        if (user.getId() != sessionData.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not your toDo");
        }

        user.updateUser(dto.getUserName(), dto.getEmail());
    }

    @Override
    @Transactional
    public void deleteUser(Long id, UserResponseDto sessionData) {

        isIdNull(id);

        User user = userRepository.findByIdOrElseThrow(id);

        if (user.getId() != sessionData.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not your toDo");
        }

        user.deleteUser();
    }

    @Override
    public UserResponseDto login(LoginRequestDto dto) {

        List<User> users = userRepository.findByEmailAndPasswordAndDeletedFalse(dto.getEmail(), dto.getPassword());

        User user = users.stream().findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 입력입니다."));

        return new UserResponseDto(user.getId(), user.getUserName(), user.getEmail(), user.getDate().toLocalDate());
    }

    public void isIdNull(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id Not found");
        }
    }

}
