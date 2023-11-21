package com.ssafy.enjoytrip.travelCourses.controller;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.ResponseUtil;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseInfoRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseUpdateRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelLike.TravelLikeSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelCourse.TravelCourseInfoResponseDto;
import com.ssafy.enjoytrip.travelCourses.service.TravelCoursesService;
import com.ssafy.enjoytrip.travelCourses.service.TravelLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travel-courses")
@Api(value = "Travel Courses API", tags = "Travel Courses")
public class TravelCoursesController {

    private final TravelCoursesService travelCoursesService;
    private final TravelLikeService travelLikeService;

    @GetMapping("/")
    @ApiOperation(value = "Get the list of travel courses", notes = "List of travel courses")
    public ApiResponseDto<Object> coursesList(HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.coursesList(request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @PostMapping("/course")
    @ApiOperation(value = "Save the travel course", notes = "Save travel course")
    public ApiResponseDto<Object> courseSave(@RequestBody TravelCourseSaveRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseSave(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @GetMapping("/course")
    @ApiOperation(value = "Info of travel course", notes = "Travel course Info", response = TravelCourseInfoResponseDto.class)
    public ApiResponseDto<Object> courseInfo(@RequestParam("travelCourseId") Long travelCourseId, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseInfo(travelCourseId, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @PutMapping("/course")
    @ApiOperation(value = "Update the travel course", notes = "Travel course Update")
    public ApiResponseDto<Object> courseUpdate(@RequestBody TravelCourseUpdateRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseUpdate(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @DeleteMapping("/course")
    @ApiOperation(value = "Delete the travel course", notes = "Travel course Delete")
    public ApiResponseDto<Object> courseDelete(@RequestParam("travelCourseId") Long travelCourseId, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelCoursesService.courseDelete(travelCourseId, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @PostMapping("/like")
    @ApiOperation(value = "Save the attraction like", notes = "Travel attraction like Save")
    public ApiResponseDto<Object> likePost(@RequestBody TravelLikeSaveRequestDto requestDto, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelLikeService.likeSave(requestDto, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @GetMapping("/like")
    @ApiOperation(value = "List of attraction like", notes = "Attraction like Save")
    public ApiResponseDto<Object> likeList(HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelLikeService.likeList(request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }

    @DeleteMapping("/like")
    @ApiOperation(value = "Delete the attraction like", notes = "Attraction like Delete")
    public ApiResponseDto<Object> likeDelete(@RequestParam("likeId") Long likeId, HttpServletRequest request){
        ServiceControllerDataDto<Object> apiResponseDto = travelLikeService.likeDelete(likeId, request);
        return ResponseUtil.ok(apiResponseDto.getData(), apiResponseDto.getMsg());
    }
}
