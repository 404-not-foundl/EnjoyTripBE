package com.ssafy.enjoytrip.travelCourses.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCoursesSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.service.TravelCoursesSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travel-courses")
public class TravelCoursesController {

    private final TravelCoursesSaveService travelCourseSaveService;

    @PostMapping("/course")
    public ApiResponseDto<Void> saving(@RequestBody TravelCoursesSaveRequestDto requestDto, HttpServletResponse response){
        return ResponseUtil.ok(travelCourseSaveService.saving(requestDto));
    }
}
