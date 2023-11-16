package com.ssafy.enjoytrip.travelCourses.service;

import com.ssafy.enjoytrip.travelCourses.repository.TravelCourseScheduleRepository;
import com.ssafy.enjoytrip.travelCourses.repository.TravelCoursesRepository;
import com.ssafy.enjoytrip.travelCourses.repository.TravelMembersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelCoursesService {

    private final TravelMembersRepository travelMembersRepository;
    private final TravelCoursesRepository travelCoursesRepository;
    private final TravelCourseScheduleRepository travelCourseScheduleRepository;


}
