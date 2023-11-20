package com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse;

import lombok.*;

import java.math.BigDecimal;

@Getter@Setter
public class TravelCourseScheduleDto {
    private String name;
    private String category;
    private String address;
    private String memo;
    private String img;
    private double lat;
    private double lng;
}
