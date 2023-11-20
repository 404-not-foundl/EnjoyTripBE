package com.ssafy.enjoytrip.travelCourses.repository.TravelCourse;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourse.TravelCourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelCourseScheduleRepository extends JpaRepository<TravelCourseSchedule, Long> {
}
