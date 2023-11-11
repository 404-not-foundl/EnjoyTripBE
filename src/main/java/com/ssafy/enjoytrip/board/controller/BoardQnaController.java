package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.request.*;
import com.ssafy.enjoytrip.board.dto.response.BoardQnaGetArticleResponseDto;
import com.ssafy.enjoytrip.board.dto.response.BoardQnaListOfArticleResponseDto;
import com.ssafy.enjoytrip.board.service.BoardQnaService;
import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/board/qna")
@RequiredArgsConstructor
public class BoardQnaController {

    private final BoardQnaService boardQnaService;

    @PostMapping("/article")
    public ApiResponseDto<Void> save(@RequestBody BoardQnaSaveArticleRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResponseUtil.ok(boardQnaService.saveArticle(requestDto, request, response));
    }

    @PutMapping("/article")
    public ApiResponseDto<Void> update(@RequestBody BoardQnaUpdateArticleRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResponseUtil.ok(boardQnaService.update(requestDto, request, response));
    }

    @GetMapping("/article")
    public ApiResponseDto<BoardQnaGetArticleResponseDto> get(@RequestBody BoardQnaGetArticleRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return ResponseUtil.ok(boardQnaService.get(requestDto, request, response));
    }

    @DeleteMapping("/article")
    public ApiResponseDto<Void> delete(@RequestBody BoardQnaDeleteArticleRequestDto requestDto, HttpServletRequest request, HttpServletResponse response){
        return ResponseUtil.ok(boardQnaService.delete(requestDto, request, response));
    }

    @GetMapping("/list")
    public ApiResponseDto<BoardQnaListOfArticleResponseDto> list(@RequestBody BoardQnaListOfArticleRequestDto requestDto){
        return ResponseUtil.ok(boardQnaService.list(requestDto));
    }
}
