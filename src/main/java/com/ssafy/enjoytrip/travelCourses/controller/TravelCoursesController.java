package com.ssafy.enjoytrip.travelCourses.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseInfoRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseUpdateRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelLike.TravelLikeSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.service.TravelCoursesService;
import com.ssafy.enjoytrip.travelCourses.service.TravelLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travel-courses")
public class TravelCoursesController {

    private final TravelCoursesService travelCoursesService;
    private final TravelLikeService travelLikeService;

    @GetMapping("/")
    public ApiResponseDto<Object> coursesList(HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.coursesList(request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @PostMapping("/course")
    public ApiResponseDto<Object> courseSave(@RequestBody TravelCourseSaveRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseSave(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @GetMapping("/course")
    public ApiResponseDto<Object> courseInfo(@RequestBody TravelCourseInfoRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseInfo(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @PutMapping("/course")
    public ApiResponseDto<Object> courseUpdate(@RequestBody TravelCourseUpdateRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseUpdate(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @DeleteMapping("/course")
    public ApiResponseDto<Object> courseDelete(@RequestParam("travelCourseId") Long travelCourseId, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseDelete(travelCourseId, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @PostMapping("/like")
    public ApiResponseDto<Object> likePost(@RequestBody TravelLikeSaveRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelLikeService.likeSave(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @GetMapping("/like")
    public ApiResponseDto<Object> likeList(HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelLikeService.likeList(request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @DeleteMapping("/like")
    public ApiResponseDto<Object> likeDelete(@RequestParam("likeId") Long likeId, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelLikeService.likeDelete(likeId, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }
}
