package com.example.tododevelop.controller;


import com.example.tododevelop.dto.RegistToDoRequestDto;
import com.example.tododevelop.dto.ResponseDto;
import com.example.tododevelop.dto.UpdateToDoRequestDto;
import com.example.tododevelop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<ResponseDto> registToDo (@ModelAttribute RegistToDoRequestDto dto){

        boardService.regist(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> viewToDo (@PathVariable Long id){

        return new ResponseEntity<>(boardService.view(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDto>> allViewToDo(@RequestParam(required = false) String userName,
                                                         @RequestParam(required = false) String title){

        return new ResponseEntity<>(boardService.allView(userName, title), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ResponseDto> updateToDo(@RequestBody UpdateToDoRequestDto dto){

        boardService.updateToDo(dto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteToDo (@PathVariable Long id){

        boardService.deleteToDo(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
