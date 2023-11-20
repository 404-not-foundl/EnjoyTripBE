package com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelLike;

import lombok.Getter;

@Getter
public class TravelLikeSaveRequestDto {
    private String name;
    private String category;
    private String address;
    private String img;
    private double lat;
    private double lng;
}
