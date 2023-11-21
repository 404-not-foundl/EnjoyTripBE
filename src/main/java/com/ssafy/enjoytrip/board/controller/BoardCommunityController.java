package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.service.BoardCommunityService;
import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/board/community")
@RequiredArgsConstructor
public class BoardCommunityController {

    private final BoardCommunityService boardCommunityService;

    @GetMapping("/")
    public ApiResponseDto<Object> communityList(HttpServletRequest request){
        ApiResponseDto<Object> apiResponseDto = boardCommunityService.communityArticleList(request);
        return ResponseUtil.ok(apiResponseDto);
    }

    @PostMapping("/article")
    public
}
