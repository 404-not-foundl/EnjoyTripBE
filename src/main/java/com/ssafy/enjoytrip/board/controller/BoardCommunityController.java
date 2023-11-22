package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.dto.request.BoardCommunity.CommunityArticleListRequestDto;
import com.ssafy.enjoytrip.board.dto.request.BoardCommunity.CommunityArticlePostRequestDto;
import com.ssafy.enjoytrip.board.service.BoardCommunityService;
import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/board/community")
@RequiredArgsConstructor
public class BoardCommunityController {

    private final BoardCommunityService boardCommunityService;

    @GetMapping("/")
    public ApiResponseDto<Object> communityList(@ModelAttribute CommunityArticleListRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = boardCommunityService.communityArticleList(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @PostMapping("/article")
    public ApiResponseDto<Object> articlePost(@RequestBody CommunityArticlePostRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = boardCommunityService.communityArticlePost(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }
}
