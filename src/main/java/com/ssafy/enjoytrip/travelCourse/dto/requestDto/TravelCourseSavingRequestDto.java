package com.ssafy.enjoytrip.travelCourse.dto.requestDto;

import com.ssafy.enjoytrip.travelCourse.entity.TravelCourseListAttraction;
import lombok.Getter;

import java.util.List;

@Getter
public class TravelCourseSavingRequestDto {

    private String title;
    private String startDate;
    private String endDate;

    private List<TravelCourseListAttraction> courseInfo;
}
