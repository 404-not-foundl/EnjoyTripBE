package com.ssafy.enjoytrip.travelCourses.dto.responseDto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
public class CourseInfoDto {
    private String name;
    private String category;
    private String address;
    private String memo;
    private String image;
}
