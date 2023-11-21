package com.ssafy.enjoytrip.board.service;

import com.ssafy.enjoytrip.board.dto.request.BoardCommunity.CommunityListRequestDto;
import com.ssafy.enjoytrip.board.repository.BoardCommunityRepository;
import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class BoardCommunityService {

    private final BoardCommunityRepository boardCommunityRepository;

    public ApiResponseDto<Object> communityArticleList(HttpServletRequest request){
        return null;
    }

    public ApiResponseDto<Object> communityArticlePost(CommunityListRequestDto requestDto, HttpServletRequest request){
        return null;
    }

}
