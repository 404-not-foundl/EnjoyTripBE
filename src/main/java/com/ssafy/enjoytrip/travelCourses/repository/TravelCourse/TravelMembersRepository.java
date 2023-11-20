package com.ssafy.enjoytrip.travelCourses.repository.TravelCourse;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourse.TravelMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelMembersRepository extends JpaRepository<TravelMembers, Long> {
}
