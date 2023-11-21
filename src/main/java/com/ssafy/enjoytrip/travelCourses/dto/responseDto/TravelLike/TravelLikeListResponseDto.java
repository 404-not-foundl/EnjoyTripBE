package com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelLike;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
public class TravelLikeListResponseDto {

    private Long id;
    private String name;
    private String category;
    private String address;
    private String img;
    private double lat;
    private double lng;
}
