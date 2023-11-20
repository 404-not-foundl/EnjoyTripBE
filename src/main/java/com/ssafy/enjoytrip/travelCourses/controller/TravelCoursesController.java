package com.ssafy.enjoytrip.travelCourses.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourseDeleteRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourseInfoRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourseSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourseUpdateRequestDto;
import com.ssafy.enjoytrip.travelCourses.service.TravelCoursesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travel-courses")
public class TravelCoursesController {

    private final TravelCoursesService travelCoursesService;

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
    public ApiResponseDto<Object> courseDelete(@RequestBody TravelCourseDeleteRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseDelete(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }
}
