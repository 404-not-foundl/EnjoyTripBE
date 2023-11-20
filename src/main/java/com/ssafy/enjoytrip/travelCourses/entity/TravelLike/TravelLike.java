package com.ssafy.enjoytrip.travelCourses.entity.TravelLike;

import com.ssafy.enjoytrip.common.entity.BaseTime;
import com.ssafy.enjoytrip.users.entity.Users;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TravelLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String name;
    private String category;
    private String address;
    private String image;
    private double latitude;
    private double longitude;
}
