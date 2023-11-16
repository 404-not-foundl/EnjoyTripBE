package com.ssafy.enjoytrip.travelCourses.repository;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelCoursesRepository extends JpaRepository<TravelCourses, Long> {
}
