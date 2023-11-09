package com.ssafy.enjoytrip.travelCourse.service;

import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.travelCourse.dto.requestDto.TravelCourseSavingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TravelCourseSaveService {

    @Transactional
    public MsgType saving(TravelCourseSavingRequestDto requestDto){

        return MsgType.TRAVEL_COURSE_LIST_SAVE_COMPLETE;
    }
}
