package com.ssafy.enjoytrip.travelCourses.entity.TravelCourse;

import com.ssafy.enjoytrip.common.entity.BaseTime;
import com.ssafy.enjoytrip.users.entity.Users;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelCourses extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String travelTitle;
    private String startDate;
    private String endDate;
    private int travelDays;

    @OneToMany(mappedBy = "travelCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TravelMembers> travelMembers = new ArrayList<>();

    @OneToMany(mappedBy = "travelCourse", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    List<TravelCourseSchedule> travelCourseSchedules = new ArrayList<>();

}

