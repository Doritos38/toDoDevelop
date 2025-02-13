package com.example.tododevelop.service;

import com.example.tododevelop.config.PasswordEncoder;
import com.example.tododevelop.dto.*;
import com.example.tododevelop.entity.User;
import com.example.tododevelop.repository.UserRepository;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void regist(RegistUserRequestDto dto) {

        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User(dto.getUserName(), dto.getEmail(), encodedPassword);

        userRepository.save(user);
    }

    @Override
    public UserResponseDto view(Long id) {

        if(verify(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong range id");
        }

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

        User user = userRepository.findByIdAndUserIdOrElseThrow(dto.getId(), sessionData.getId());

        user.updateUser(dto.getUserName(), dto.getEmail());
    }

    @Override
    @Transactional
    public void deleteUser(Long id, UserResponseDto sessionData) {

        if(verify(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong range id");
        }

        User user = userRepository.findByIdAndUserIdOrElseThrow(id, sessionData.getId());

        user.deleteUser();
    }

    @Override
    public UserResponseDto login(LoginRequestDto dto) {

        List<User> users = userRepository.findByEmail(dto.getEmail());

        User user = users.stream().findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong email/pw"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong email/pw");
        }

        return new UserResponseDto(user.getId(), user.getUserName(), user.getEmail(), user.getDate().toLocalDate());
    }

    public boolean verify(Long id) {
        if (id < 1 || id > 500) {
            return true;
        }
        return false;
    }
}
