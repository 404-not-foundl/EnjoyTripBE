package com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelCourse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
public class TravelCourseListDto {
    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private int travelDays;
}
