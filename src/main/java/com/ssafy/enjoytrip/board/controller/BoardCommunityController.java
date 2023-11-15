package com.ssafy.enjoytrip.board.controller;

import com.ssafy.enjoytrip.board.service.BoardCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/community")
@RequiredArgsConstructor
public class BoardCommunityController {

    private final BoardCommunityService boardCommunityService;


}
