package com.ssafy.enjoytrip.users.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.users.dto.request.CheckDuplicateDto;
import com.ssafy.enjoytrip.users.dto.request.JoinRequestDto;
import com.ssafy.enjoytrip.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/user")
    public ApiResponseDto<Void> join(@RequestBody JoinRequestDto requestDto){
        return ResponseUtil.ok(usersService.join(requestDto));
    }

    @PostMapping("/check-id")
    public ApiResponseDto<Boolean> checkId(@RequestBody CheckDuplicateDto requestDto){
        return ResponseUtil.ok(usersService.checkId(requestDto));
    }

    @PostMapping("/check-nickname")
    public ApiResponseDto<Boolean> checkNickname(@RequestBody CheckDuplicateDto requestDto){
        return ResponseUtil.ok(usersService.checkNickname(requestDto));
    }
}
