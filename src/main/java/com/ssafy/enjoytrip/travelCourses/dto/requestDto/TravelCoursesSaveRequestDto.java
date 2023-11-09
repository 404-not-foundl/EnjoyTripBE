package com.ssafy.enjoytrip.travelCourses.dto.requestDto;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCoursesListAttraction;
import lombok.Getter;

import java.util.List;

@Getter
public class TravelCoursesSaveRequestDto {

    private String title;
    private String startDate;
    private String endDate;

    private List<TravelCoursesListAttraction> courseInfo;
}
