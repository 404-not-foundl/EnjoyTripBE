package com.ssafy.enjoytrip.travelCourses.entity.TravelCourse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.enjoytrip.users.entity.Users;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TravelMembers {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "travel_courses_id")  // Corrected to match the column name in TravelCourses
    @JsonIgnore
    private TravelCourses travelCourse;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
