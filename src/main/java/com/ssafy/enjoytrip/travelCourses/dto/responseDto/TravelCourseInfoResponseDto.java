package com.ssafy.enjoytrip.travelCourses.dto.responseDto;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourseSchedule;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter@Builder
public class TravelCourseInfoResponseDto {
    private Long travelCourseId;
    private String title;
    private String startDate;
    private String endDate;
    private int totalDays;
    List<List<CourseInfoDto>> coursesInfo;
}
