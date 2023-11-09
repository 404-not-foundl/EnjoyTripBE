package com.ssafy.enjoytrip.travelCourses.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TravelCoursesListAttraction extends TravelCoursesAttraction {

    private String memo;
    private int index;
    private int day;
}
