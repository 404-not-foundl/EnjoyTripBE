package com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelLike;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
public class TravelLikeListResponseDto {

    private String contentId;
    private String contentTypeId;
    private String name;
    private String category;
    private String address;
    private String img;
    private double lat;
    private double lng;
}
