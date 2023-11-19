package com.ssafy.enjoytrip.travelCourses.dto.requestDto;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourseSchedule;
import lombok.Getter;

import java.util.List;

@Getter
public class TravelCourseUpdateRequestDto {
    private Long travelCourseId;
    private String title;
    private String startDate;
    private String endDate;
    private int totalDays;
    private List<List<TravelCourseSchedule>> courseInfo;
}