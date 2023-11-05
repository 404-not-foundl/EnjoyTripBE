package com.ssafy.enjoytrip.users.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.users.dto.request.CheckDuplicateDto;
import com.ssafy.enjoytrip.users.dto.request.FindPasswordRequestDto;
import com.ssafy.enjoytrip.users.dto.request.JoinRequestDto;
import com.ssafy.enjoytrip.users.dto.request.LoginRequestDto;
import com.ssafy.enjoytrip.users.dto.response.FindPasswordResponsDto;
import com.ssafy.enjoytrip.users.dto.response.UserInfoDto;
import com.ssafy.enjoytrip.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @PutMapping("/new-password")
    public ApiResponseDto<Boolean> findPassword(@RequestBody FindPasswordRequestDto requestDto){
        return ResponseUtil.ok(usersService.findPassword(requestDto));
    }

    @PostMapping("/login")
    public ApiResponseDto<Boolean> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        return ResponseUtil.ok(usersService.login(requestDto, response));
    }

    @PostMapping("/logout")
    public ApiResponseDto<Void> logout(HttpServletResponse response){
        return ResponseUtil.ok(usersService.logout(response));
    }

    @GetMapping("/user")
    @ResponseBody
    public ApiResponseDto<UserInfoDto> userInfo(HttpServletRequest request){
        return ResponseUtil.ok(usersService.userInfo(request));
    }

    @DeleteMapping("/user")
    public ApiResponseDto<Void> deleteUser(HttpServletRequest request){
        usersService.deleteUser(request);
        return ResponseUtil.ok(MsgType.USER_DELETED_COMPLETE);
    }
}
