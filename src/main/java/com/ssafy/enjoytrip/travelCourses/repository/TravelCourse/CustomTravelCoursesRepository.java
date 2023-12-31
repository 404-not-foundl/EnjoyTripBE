package com.ssafy.enjoytrip.travelCourses.repository.TravelCourse;

import com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelCourse.TravelCourseListDto;
import com.ssafy.enjoytrip.travelCourses.entity.TravelCourse.TravelCourses;
import com.ssafy.enjoytrip.users.entity.Users;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomTravelCoursesRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<TravelCourseListDto> findDistinctByUserWithDetails(Users user) {
        String jpqlQuery = "SELECT DISTINCT tc FROM TravelCourses tc " +
                "LEFT JOIN FETCH tc.travelMembers tm " +
                "WHERE tm.user = :user AND tc.deletedDate IS NULL";

        TypedQuery<TravelCourses> query = entityManager.createQuery(jpqlQuery, TravelCourses.class);
        query.setParameter("user", user);

        List<TravelCourseListDto> travelCourseList = new ArrayList<>();
        for(TravelCourses travelCourses : query.getResultList()){
            TravelCourseListDto travelCourseListDto = TravelCourseListDto.builder()
                    .travelCourseId(travelCourses.getId())
                    .title(travelCourses.getTravelTitle())
                    .startDate(travelCourses.getStartDate())
                    .endDate(travelCourses.getEndDate())
                    .totalDays(travelCourses.getTravelDays())
                    .build();
            travelCourseList.add(travelCourseListDto);
        }
        return travelCourseList;
    }
}
