package com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelCourse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import io.swagger.annotations.ApiModel;

@Getter@Setter@Builder
@ApiModel(description = "Response DTO for Travel Course Information")
public class TravelCourseInfoResponseDto {
    private Long travelCourseId;
    private String title;
    private String startDate;
    private String endDate;
    private int totalDays;
    List<List<CourseInfoDto>> coursesInfo;
}
