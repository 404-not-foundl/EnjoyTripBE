package com.ssafy.enjoytrip.travelCourses.repository;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourseSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelCourseScheduleRepository extends JpaRepository<TravelCourseSchedule, Long> {
}
