package com.ssafy.enjoytrip.travelCourses.repository;

import com.ssafy.enjoytrip.travelCourses.entity.TravelCourseSchedule;
import com.ssafy.enjoytrip.travelCourses.entity.TravelCourses;
import com.ssafy.enjoytrip.users.entity.Users;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TravelCoursesRepository extends JpaRepository<TravelCourses, Long> {
    List<TravelCourses> findAllByTravelMembersUser_IdAndDeletedDateIsNull(Long userId);
    Optional<TravelCourses> findTravelCoursesByIdAndDeletedDateIsNull(Long id);
    @Query("SELECT DISTINCT tc FROM TravelCourses tc " +
            "LEFT JOIN FETCH tc.travelMembers tm " +
            "LEFT JOIN FETCH tc.travelCourseSchedules tcs " +
            "WHERE tm.user = :user AND tc.deletedDate IS NULL")
    List<TravelCourses> findByUserWithDetails(@Param("user") Users user);
}
