package com.ssafy.enjoytrip.travelCourse.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.travelCourse.dto.requestDto.TravelCourseSavingRequestDto;
import com.ssafy.enjoytrip.travelCourse.service.TravelCourseSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travel-courses")
public class TravelCourseController {

    private final TravelCourseSaveService travelCourseSaveService;

    @PostMapping("/course")
    public ApiResponseDto<Void> saving(@RequestBody TravelCourseSavingRequestDto requestDto, HttpServletResponse response){
        return ResponseUtil.ok(travelCourseSaveService.saving(requestDto));
    }
}
