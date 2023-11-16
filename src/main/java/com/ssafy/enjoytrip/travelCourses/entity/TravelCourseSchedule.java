package com.ssafy.enjoytrip.travelCourses.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelCourseSchedule {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "travel_courses_id")
    private TravelCourses travelCourse;

    private String name;
    private String category;
    private String address;
    private String memo;
    private String image;
    private int index;
    private int day;
}
