package com.ssafy.enjoytrip.travelCourses.repository.TravelCourse;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourse.TravelCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TravelCoursesRepository extends JpaRepository<TravelCourses, Long> {
    Optional<TravelCourses> findTravelCoursesByIdAndDeletedDateIsNull(Long id);
}
