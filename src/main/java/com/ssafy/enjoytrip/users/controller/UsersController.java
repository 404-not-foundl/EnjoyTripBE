package com.ssafy.enjoytrip.users.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.users.dto.request.*;
import com.ssafy.enjoytrip.users.dto.response.CacheImageToProfileImageResponseDto;
import com.ssafy.enjoytrip.users.dto.response.CacheImageUpdateResponseDto;
import com.ssafy.enjoytrip.users.dto.response.FindPasswordResponsDto;
import com.ssafy.enjoytrip.users.dto.response.UserInfoDto;
import com.ssafy.enjoytrip.users.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "Users API", tags = "Users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/user")
    @ApiOperation(value = "Join Users", notes = "Join Users to the server")
    public ApiResponseDto<Void> join(@RequestBody JoinRequestDto requestDto){
        return ResponseUtil.ok(usersService.join(requestDto));
    }

    @PostMapping("/check-id")
    @ApiOperation(value = "Check Login Id", notes = "Checking Login Id for duplication")
    public ApiResponseDto<Boolean> checkId(@RequestBody CheckDuplicateDto requestDto){
        return ResponseUtil.ok(usersService.checkId(requestDto));
    }

    @PostMapping("/check-nickname")
    @ApiOperation(value = "Check Nickname", notes = "Checking Nickname for duplication")
    public ApiResponseDto<Boolean> checkNickname(@RequestBody CheckDuplicateDto requestDto){
        return ResponseUtil.ok(usersService.checkNickname(requestDto));
    }

    @PutMapping("/new-password")
    @ApiOperation(value = "New Password", notes = "Changing password to new password")
    public ApiResponseDto<Void> changePassword(@RequestBody ChangePasswordRequestDto requestDto, HttpServletRequest request){
        return ResponseUtil.ok(usersService.changePassword(requestDto, request));
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login", notes = "Logging in giving cookies")
    public ApiResponseDto<Boolean> login(@RequestBody LoginRequestDto requestDto, HttpServletRequest request, HttpServletResponse response){
        return ResponseUtil.ok(usersService.login(requestDto, request, response));
    }

    @PostMapping("/logout")
    @ApiOperation(value = "Logout", notes = "Logging out deleting cookies client have as userId")
    public ApiResponseDto<Void> logout(HttpServletResponse response){
        return ResponseUtil.ok(usersService.logout(response));
    }

    @GetMapping("/user")
    @ResponseBody
    @ApiOperation(value = "User Info", notes = "Giving User info based on cookie")
    public ApiResponseDto<UserInfoDto> userInfo(HttpServletRequest request){
        return ResponseUtil.ok(usersService.userInfo(request));
    }

    @DeleteMapping("/user")
    @ApiOperation(value = "Delete Account", notes = "User Delete date set to LocalTime")
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

    @DeleteMapping ("/cache-image")
    @ApiOperation(value = "Clear Cache Image", notes = "Clearing the Cache Image matching the profile Image")
    public ApiResponseDto<Boolean> clearCacheImage(HttpServletRequest request){
        return ResponseUtil.ok(usersService.clearCacheImage(request));
    }

    @PostMapping("/cache-image")
    @ApiOperation(value = "Change Cache Image", notes = "Change the Cache Image based on upload file")
    public ApiResponseDto<CacheImageUpdateResponseDto> changeCacheImage(@RequestParam("userImage")MultipartFile userImage, HttpServletRequest request){
        return ResponseUtil.ok(usersService.changeCacheImage(userImage, request));
    }

    @PutMapping("/cache-image")
    @ApiOperation(value = "Cache Image to Profile Image", notes = "Changing Profile image to Cache image")
    public ApiResponseDto<CacheImageToProfileImageResponseDto> cacheImgToProfileImg(HttpServletRequest request){
        return ResponseUtil.ok(usersService.cacheImgToProfileImg(request));
    }
}
