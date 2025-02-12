package com.example.tododevelop.service;


import com.example.tododevelop.dto.AllToDoRequestDto;
import com.example.tododevelop.dto.RegistToDoRequestDto;
import com.example.tododevelop.dto.ToDoResponseDto;
import com.example.tododevelop.dto.UpdateToDoRequestDto;
import com.example.tododevelop.entity.ToDo;
import com.example.tododevelop.entity.User;
import com.example.tododevelop.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final com.example.tododevelop.repository.UserRepository userRepository;

    @Override
    @Transactional
    public void regist(RegistToDoRequestDto dto) {

        User user = userRepository.findByIdOrElseThrow(dto.getUserId());

        ToDo toDo = new ToDo(dto.getTitle(), dto.getContents(), user);

        boardRepository.save(toDo);
    }

    @Override
    public ToDoResponseDto view(Long id) {

        ToDo toDo = boardRepository.findByIdOrElseThrow(id);

        ToDoResponseDto toDoResponseDto = new ToDoResponseDto(toDo);

        return toDoResponseDto;
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
    public void updateToDo(UpdateToDoRequestDto dto) {

        ToDo toDo = boardRepository.findByIdOrElseThrow(dto.getId());

        toDo.updateToDo(dto.getTitle(), dto.getContents());
    }

    @Override
    @Transactional
    public void deleteToDo(Long id) {

        ToDo toDo = boardRepository.findByIdOrElseThrow(id);

        toDo.deleteToDo();
    }


}
