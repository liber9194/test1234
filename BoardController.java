package com.example.preproject.controller;

import com.example.preproject.dto.BoardRequestDto;
import com.example.preproject.dto.BoardResponseDto;
import com.example.preproject.entity.Board;
import com.example.preproject.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class BoardController {
    private final BoardService boardService;
        
    @GetMapping("")
    public List<BoardResponseDto> getBoards()
    {
        return boardService.getBoards();
    }

    @GetMapping("/{postId}")
    public BoardResponseDto getSingleBoard(@PathVariable Long postId)
    {
        return boardService.getSingleBoard(postId);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long postId,
                                                        @RequestBody BoardRequestDto requestDto)
    {
        BoardResponseDto boardResponseDto = boardService.updateBoard(postId, requestDto);
        return new ResponseEntity<>(boardResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Object> deleteBoard(@PathVariable Long postId)
    {
        try{
            boardService.deleteBoard(postId);
        } catch (Exception e)
        {
            return new ResponseEntity<>("게시글을 삭제하는 데 실패하였습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> writeBoard(@RequestBody BoardRequestDto requestDto)
    {
        try {
            boardService.writeBoard(requestDto);
        } catch (Exception e)
        {
            return new ResponseEntity<>("게시글을 작성하는 데 실패하였습니다", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("게시글이 작성되었습니다.",HttpStatus.OK);
    }
}
