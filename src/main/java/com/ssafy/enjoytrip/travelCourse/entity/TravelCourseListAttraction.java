package com.ssafy.enjoytrip.travelCourse.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelCourseListAttraction extends TravelCourseAttraction{

    private String memo;
    private int index;
    private int day;
}
