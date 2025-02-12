package com.example.tododevelop.service;


import com.example.tododevelop.dto.RegistToDoRequestDto;
import com.example.tododevelop.dto.ResponseDto;
import com.example.tododevelop.dto.UpdateToDoRequestDto;
import com.example.tododevelop.entity.ToDo;
import com.example.tododevelop.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void regist(RegistToDoRequestDto dto) {

        ToDo toDo = new ToDo(dto.getUserName(), dto.getTitle(), dto.getContents());

        boardRepository.save(toDo);
    }

    @Override
    public ResponseDto view(Long id) {

        ToDo toDo = boardRepository.findByIdOrElseThrow(id);

        ResponseDto responseDto = new ResponseDto(toDo);

        return responseDto;
    }

    @Override
    public List<ResponseDto> allView(String userName, String title) {
        System.out.println("asdsdfasdfasd                           "+userName+title);


        List<ToDo> allByConditions = boardRepository.findAllByConditions(userName, title);


        return allByConditions.stream().map(ResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateToDo(UpdateToDoRequestDto updateToDoDto) {

        ToDo toDo = boardRepository.findByIdOrElseThrow(updateToDoDto.getId());

        toDo.updateToDo(updateToDoDto.getTitle(), updateToDoDto.getContents());
    }

    @Override
    @Transactional
    public void deleteToDo(Long id) {

        ToDo toDo = boardRepository.findByIdOrElseThrow(id);

        toDo.deleteToDo();
        System.out.println(toDo.isDeleted());
    }


}
