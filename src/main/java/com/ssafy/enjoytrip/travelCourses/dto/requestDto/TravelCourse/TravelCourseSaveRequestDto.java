package com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse;

import lombok.Getter;

import java.util.List;

@Getter
public class TravelCourseSaveRequestDto {
    private String title;
    private String startDate;
    private String endDate;
    private int totalDays;
    private List<List<TravelCourseScheduleDto>> courseInfo;
}
