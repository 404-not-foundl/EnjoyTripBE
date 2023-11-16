package com.ssafy.enjoytrip.users.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.users.dto.request.*;
import com.ssafy.enjoytrip.users.dto.response.FindPasswordResponsDto;
import com.ssafy.enjoytrip.users.dto.response.UserInfoDto;
import com.ssafy.enjoytrip.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ApiResponseDto<Void> changePassword(@RequestBody ChangePasswordRequestDto requestDto, HttpServletRequest request){
        return ResponseUtil.ok(usersService.changePassword(requestDto, request));
    }

    @PostMapping("/login")
    public ApiResponseDto<Boolean> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request, HttpServletResponse response){
        return ResponseUtil.ok(usersService.login(requestDto, request, response));
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

    @PostMapping("/image")
    public ApiResponseDto<Void> image(@RequestParam("userImage")MultipartFile userImage, HttpServletRequest request){
        return ResponseUtil.ok(usersService.profileImage(userImage, request));
    }

    @DeleteMapping("/image")
    public ApiResponseDto<Void> deleteImage(HttpServletRequest request){
        return ResponseUtil.ok(usersService.deleteProfileImage(request));
    }

//    @GetMapping("/image")
//    public ResponseEntity<Resource> showImage(@RequestParam("filename") String filename){
//        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(usersService.getImage(filename));
//    }

    @PostMapping("/cache-image")
    public ApiResponseDto<Boolean> updateCacheImage(HttpServletRequest request){
        return ResponseUtil.ok(usersService.cacheImageChange(request));
    }
}
