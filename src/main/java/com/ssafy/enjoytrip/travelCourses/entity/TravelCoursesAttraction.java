package com.ssafy.enjoytrip.travelCourses.entity;

import com.ssafy.enjoytrip.common.entity.BaseTime;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelCoursesAttraction extends BaseTime {

    @Id @GeneratedValue
    private Long travelCoursesAttractionId;

    private String name;
    private String category;
    private String address;
    private String img;
}
