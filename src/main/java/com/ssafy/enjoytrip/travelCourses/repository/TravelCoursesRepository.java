package com.ssafy.enjoytrip.travelCourses.repository;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourseSchedule;
import com.ssafy.enjoytrip.travelCourses.entity.TravelCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelCoursesRepository extends JpaRepository<TravelCourses, Long> {
    List<TravelCourses> findAllByTravelMembersUser_IdAndDeletedDateIsNotNull(Long userId);
    Optional<TravelCourses> findTravelCoursesByIdAndDeletedDateIsNotNull(Long id);
}
