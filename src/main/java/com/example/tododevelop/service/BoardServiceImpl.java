package com.example.tododevelop.service;


import com.example.tododevelop.dto.*;
import com.example.tododevelop.entity.ToDo;
import com.example.tododevelop.entity.User;
import com.example.tododevelop.repository.BoardRepository;
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
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void regist(RegistToDoRequestDto dto, UserResponseDto sessionData) {

        User user = userRepository.findByIdOrElseThrow(sessionData.getId());

        ToDo toDo = new ToDo(dto.getTitle(), dto.getContents(), user);

        boardRepository.save(toDo);
    }

    @Override
    public ToDoResponseDto view(Long id) {

        isIdNull(id);

        ToDo toDo = boardRepository.findByIdOrElseThrow(id);

        ToDoResponseDto dto = new ToDoResponseDto(toDo);

        return dto;
    }

    @Override
    public List<ToDoResponseDto> viewAll(AllToDoRequestDto dto) {

        LocalDate date;

        if(dto.getDate() == null){
            date = null;
        }else{
            date = LocalDate.parse(dto.getDate());
        }

        List<ToDo> allByConditions = boardRepository.findAllByConditions(dto.getUserName(), dto.getTitle(), date);


        return allByConditions.stream().map(ToDoResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateToDo(UpdateToDoRequestDto dto, UserResponseDto sessionData) {

        ToDo toDo = boardRepository.findByIdAndUserIdOrElseThrow(dto.getId(), sessionData.getId());

        toDo.updateToDo(dto.getTitle(), dto.getContents());
    }

    @Override
    @Transactional
    public void deleteToDo(Long id, UserResponseDto sessionData) {

        isIdNull(id);

        ToDo toDo = boardRepository.findByIdAndUserIdOrElseThrow(id, sessionData.getId());

        toDo.deleteToDo();
    }

    public void isIdNull(Long id){
        if(id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id Not found");
        }
    }


}
