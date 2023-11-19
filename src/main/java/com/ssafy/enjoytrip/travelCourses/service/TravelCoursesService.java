package com.ssafy.enjoytrip.travelCourses.service;

import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourseDeleteRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourseInfoRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourseSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourseUpdateRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.responseDto.CourseInfoDto;
import com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelCourseInfoResponseDto;
import com.ssafy.enjoytrip.travelCourses.entity.TravelCourseSchedule;
import com.ssafy.enjoytrip.travelCourses.entity.TravelCourses;
import com.ssafy.enjoytrip.travelCourses.entity.TravelMembers;
import com.ssafy.enjoytrip.travelCourses.repository.TravelCourseScheduleRepository;
import com.ssafy.enjoytrip.travelCourses.repository.TravelCoursesRepository;
import com.ssafy.enjoytrip.travelCourses.repository.TravelMembersRepository;
import com.ssafy.enjoytrip.users.entity.Users;
import com.ssafy.enjoytrip.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelCoursesService {

    private final UsersRepository usersRepository;
    private final TravelMembersRepository travelMembersRepository;
    private final TravelCoursesRepository travelCoursesRepository;
    private final TravelCourseScheduleRepository travelCourseScheduleRepository;

    public ServiceControllerDataDto<Object> courseSave(TravelCourseSaveRequestDto requestDto, HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            List<TravelCourseSchedule> scheduleList = new ArrayList<>();
            for(int i = 0; i < requestDto.getTotalDays(); i++){
                for(int j = 0; j < requestDto.getCourseInfo().size(); i++){
                    TravelCourseSchedule schedule = TravelCourseSchedule.builder()
                            .name(requestDto.getCourseInfo().get(i).get(j).getName())
                            .category(requestDto.getCourseInfo().get(i).get(j).getCategory())
                            .address(requestDto.getCourseInfo().get(i).get(j).getAddress())
                            .memo(requestDto.getCourseInfo().get(i).get(j).getMemo())
                            .image(requestDto.getCourseInfo().get(i).get(j).getImage())
                            .index(j)
                            .day(i)
                            .build();
                    scheduleList.add(schedule);
                }
            }

            List<TravelMembers> travelMembersList = new ArrayList<>();
            TravelMembers travelMembers = TravelMembers.builder()
                    .user(user)
                    .build();
            travelMembersList.add(travelMembers);

            TravelCourses travelCourses = TravelCourses.builder()
                    .user(user)
                    .travelTitle(requestDto.getTitle())
                    .startDate(requestDto.getStartDate())
                    .endDate(requestDto.getEndDate())
                    .travelDays(requestDto.getTotalDays())
                    .travelCourseSchedules(scheduleList)
                    .travelMembers(travelMembersList)
                    .build();

            travelCoursesRepository.save(travelCourses);

            return ServiceControllerDataDto.builder()
                    .data(true)
                    .msg(MsgType.TRAVEL_COURSE_SAVE_COMPLETE)
                    .build();
        }

        return ServiceControllerDataDto.builder()
                .data(false)
                .msg(MsgType.USER_NOT_FOUND)
                .build();
    }

    public ServiceControllerDataDto<Object> coursesList(HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            List<TravelCourses> travelCoursesList = travelCoursesRepository.findAllByTravelMembersUser_IdAndDeletedDateIsNotNull(user.getId());
            return ServiceControllerDataDto.builder()
                    .data(travelCoursesList)
                    .build();
        }

        return ServiceControllerDataDto.builder()
                .data(false)
                .msg(MsgType.USER_NOT_FOUND)
                .build();
    }

    public ServiceControllerDataDto<Object> courseInfo(TravelCourseInfoRequestDto requestDto, HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            TravelCourses travelCourses = travelCoursesRepository.findTravelCoursesByIdAndDeletedDateIsNotNull(requestDto.getTravelCourseId()).orElse(null);
            if(travelCourses == null){
                return ServiceControllerDataDto.builder()
                        .data(false)
                        .msg(MsgType.NO_SUCH_TRAVEL_COURSE)
                        .build();
            }

            List<TravelCourseSchedule> schedules = travelCourses.getTravelCourseSchedules();

            // Organize schedules by day and index
            Map<Integer, List<TravelCourseSchedule>> dayScheduleMap = schedules.stream()
                    .collect(Collectors.groupingBy(TravelCourseSchedule::getDay));

            // Sort each day's schedules by index
            dayScheduleMap.forEach((day, daySchedules) ->
                    daySchedules.sort((schedule1, schedule2) -> Integer.compare(schedule1.getIndex(), schedule2.getIndex())));

            // Sort the map by day
            List<List<TravelCourseSchedule>> orderedLists = dayScheduleMap.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());

            // Convert TravelCourseSchedule entities to CourseInfoDto
            List<List<CourseInfoDto>> orderedCourseInfo = orderedLists.stream()
                    .map(daySchedules ->
                            daySchedules.stream()
                                    .map(this::convertToCourseInfoDto)
                                    .collect(Collectors.toList()))
                    .collect(Collectors.toList());

            TravelCourseInfoResponseDto responseDto = TravelCourseInfoResponseDto.builder()
                    .travelCourseId(travelCourses.getId())
                    .title(travelCourses.getTravelTitle())
                    .startDate(travelCourses.getStartDate())
                    .endDate(travelCourses.getEndDate())
                    .totalDays(travelCourses.getTravelDays())
                    .coursesInfo(orderedCourseInfo)
                    .build();

            return ServiceControllerDataDto.builder()
                    .data(responseDto)
                    .msg(MsgType.TRAVEL_COURSE_INFO_SEND_COMPLETE)
                    .build();
        }

        return ServiceControllerDataDto.builder()
                .data(false)
                .msg(MsgType.USER_NOT_FOUND)
                .build();
    }

    public ServiceControllerDataDto<Object> courseUpdate(TravelCourseUpdateRequestDto requestDto, HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            TravelCourses travelCourses = travelCoursesRepository.findTravelCoursesByIdAndDeletedDateIsNotNull(requestDto.getTravelCourseId()).orElse(null);
            if(travelCourses == null){
                return ServiceControllerDataDto.builder()
                        .data(false)
                        .msg(MsgType.NO_SUCH_TRAVEL_COURSE)
                        .build();
            }

            List<TravelCourseSchedule> scheduleList = new ArrayList<>();
            for(int i = 0; i < requestDto.getTotalDays(); i++){
                for(int j = 0; j < requestDto.getCourseInfo().size(); i++){
                    TravelCourseSchedule schedule = TravelCourseSchedule.builder()
                            .name(requestDto.getCourseInfo().get(i).get(j).getName())
                            .category(requestDto.getCourseInfo().get(i).get(j).getCategory())
                            .address(requestDto.getCourseInfo().get(i).get(j).getAddress())
                            .memo(requestDto.getCourseInfo().get(i).get(j).getMemo())
                            .image(requestDto.getCourseInfo().get(i).get(j).getImage())
                            .index(j)
                            .day(i)
                            .build();
                    scheduleList.add(schedule);
                }
            }

            travelCourses.setTravelCourseSchedules(scheduleList);
            travelCoursesRepository.save(travelCourses);

            return ServiceControllerDataDto.builder()
                    .data(true)
                    .msg(MsgType.TRAVEL_COURSE_UPDATE_COMPLETE)
                    .build();
        }

        return ServiceControllerDataDto.builder()
                .data(false)
                .msg(MsgType.USER_NOT_FOUND)
                .build();
    }

    public ServiceControllerDataDto<Object> courseDelete(TravelCourseDeleteRequestDto requestDto, HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            TravelCourses travelCourses = travelCoursesRepository.findTravelCoursesByIdAndDeletedDateIsNotNull(requestDto.getTravelCourseId()).orElse(null);
            if(travelCourses == null){
                return ServiceControllerDataDto.builder()
                        .data(false)
                        .msg(MsgType.NO_SUCH_TRAVEL_COURSE)
                        .build();
            }

            travelCourses.setDeletedDate(LocalDateTime.now());
            travelCoursesRepository.save(travelCourses);

            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.TRAVEL_COURSE_DELETE_COMPLETE)
                    .build();
        }

        return ServiceControllerDataDto.builder()
                .data(false)
                .msg(MsgType.USER_NOT_FOUND)
                .build();
    }

    private Cookie checkCookieUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userId")){
                    return cookie;
                }
            }
        }

        return null;
    }

    private CourseInfoDto convertToCourseInfoDto(TravelCourseSchedule schedule) {
        return CourseInfoDto.builder()
                .name(schedule.getName())
                .category(schedule.getCategory())
                .address(schedule.getAddress())
                .memo(schedule.getMemo())
                .image(schedule.getImage())
                .build();
    }
}

/*
String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){

        }

        return ServiceControllerDataDto.builder()
                .data(false)
                .msg(MsgType.USER_NOT_FOUND)
                .build();
 */