package com.ssafy.enjoytrip.travelCourses.service;

import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCoursesSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TravelCoursesSaveService {

    @Transactional
    public MsgType saving(TravelCoursesSaveRequestDto requestDto){

        return MsgType.TRAVEL_COURSE_LIST_SAVE_COMPLETE;
    }
}
