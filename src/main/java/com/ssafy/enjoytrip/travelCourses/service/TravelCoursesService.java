package com.ssafy.enjoytrip.travelCourses.service;

import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseInfoRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelCourse.TravelCourseUpdateRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelCourse.CourseInfoDto;
import com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelCourse.TravelCourseInfoResponseDto;
import com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelCourse.TravelCourseListDto;
import com.ssafy.enjoytrip.travelCourses.entity.TravelCourse.TravelCourseSchedule;
import com.ssafy.enjoytrip.travelCourses.entity.TravelCourse.TravelCourses;
import com.ssafy.enjoytrip.travelCourses.entity.TravelCourse.TravelMembers;
import com.ssafy.enjoytrip.travelCourses.repository.TravelCourse.CustomTravelCoursesRepository;
import com.ssafy.enjoytrip.travelCourses.repository.TravelCourse.TravelCourseScheduleRepository;
import com.ssafy.enjoytrip.travelCourses.repository.TravelCourse.TravelCoursesRepository;
import com.ssafy.enjoytrip.travelCourses.repository.TravelCourse.TravelMembersRepository;
import com.ssafy.enjoytrip.users.entity.Users;
import com.ssafy.enjoytrip.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    private final CustomTravelCoursesRepository customTravelCoursesRepository;

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
            TravelCourses travelCourses = TravelCourses.builder()
                    .user(user)
                    .travelTitle(requestDto.getTitle())
                    .startDate(requestDto.getStartDate())
                    .endDate(requestDto.getEndDate())
                    .travelDays(requestDto.getTotalDays())
                    .build();

            List<TravelCourseSchedule> scheduleList = new ArrayList<>();
            for(int i = 0; i < requestDto.getCourseInfo().size(); i++){
                for(int j = 0; j < requestDto.getCourseInfo().get(i).size(); j++){
                    TravelCourseSchedule schedule = TravelCourseSchedule.builder()
                            .travelCourse(travelCourses)
                            .name(requestDto.getCourseInfo().get(i).get(j).getName())
                            .category(requestDto.getCourseInfo().get(i).get(j).getCategory())
                            .address(requestDto.getCourseInfo().get(i).get(j).getAddress())
                            .memo(requestDto.getCourseInfo().get(i).get(j).getMemo())
                            .image(requestDto.getCourseInfo().get(i).get(j).getImg())
                            .idx(j)
                            .day(i)
                            .latitude(requestDto.getCourseInfo().get(i).get(j).getLat())
                            .longitude(requestDto.getCourseInfo().get(i).get(j).getLng())
                            .build();
                    scheduleList.add(schedule);
                }
            }

            List<TravelMembers> travelMembersList = new ArrayList<>();
            TravelMembers travelMembers = TravelMembers.builder()
                    .travelCourse(travelCourses)
                    .user(user)
                    .build();
            travelMembersList.add(travelMembers);

            travelCourses.setTravelCourseSchedules(scheduleList);
            travelCourses.setTravelMembers(travelMembersList);

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
                    .data(null)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            List<TravelCourseListDto> travelCoursesList = customTravelCoursesRepository.findDistinctByUserWithDetails(user);
            return ServiceControllerDataDto.builder()
                    .data(travelCoursesList)
                    .msg(MsgType.TRAVEL_COURSE_LIST_COMPLETE)
                    .build();
        }

        return ServiceControllerDataDto.builder()
                .data(false)
                .msg(MsgType.USER_NOT_FOUND)
                .build();
    }

    public ServiceControllerDataDto<Object> courseInfo(Long travelCourseId, HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(null)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            TravelCourses travelCourses = travelCoursesRepository.findTravelCoursesByIdAndDeletedDateIsNull(travelCourseId).orElse(null);
            if(travelCourses == null){
                return ServiceControllerDataDto.builder()
                        .data(null)
                        .msg(MsgType.NO_SUCH_TRAVEL_COURSE)
                        .build();
            }


            List<TravelCourseSchedule> schedules = travelCourses.getTravelCourseSchedules();

            Map<Integer, List<TravelCourseSchedule>> dayScheduleMap = schedules.stream()
                    .collect(Collectors.groupingBy(TravelCourseSchedule::getDay));

            dayScheduleMap.forEach((day, daySchedules) ->
                    daySchedules.sort(Comparator.comparingInt(TravelCourseSchedule::getIdx)));

            List<List<CourseInfoDto>> finalSchedule = new ArrayList<>();

            for (int day = 0; day < travelCourses.getTravelDays(); day++) {
                List<TravelCourseSchedule> daySchedules = dayScheduleMap.getOrDefault(day, new ArrayList<>());
                daySchedules.sort(Comparator.comparingInt(TravelCourseSchedule::getIdx));
                List<CourseInfoDto> courseInfoList = daySchedules.stream()
                        .map(this::convertToCourseInfoDto)
                        .collect(Collectors.toList());
                finalSchedule.add(courseInfoList);
            }

            TravelCourseInfoResponseDto responseDto = TravelCourseInfoResponseDto.builder()
                    .travelCourseId(travelCourses.getId())
                    .title(travelCourses.getTravelTitle())
                    .startDate(travelCourses.getStartDate())
                    .endDate(travelCourses.getEndDate())
                    .totalDays(travelCourses.getTravelDays())
                    .coursesInfo(finalSchedule)
                    .build();

            return ServiceControllerDataDto.builder()
                    .data(responseDto)
                    .msg(MsgType.TRAVEL_COURSE_INFO_SEND_COMPLETE)
                    .build();
        }

        return ServiceControllerDataDto.builder()
                .data(null)
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
            TravelCourses travelCourses = travelCoursesRepository.findTravelCoursesByIdAndDeletedDateIsNull(requestDto.getTravelCourseId()).orElse(null);
            if(travelCourses == null){
                return ServiceControllerDataDto.builder()
                        .data(false)
                        .msg(MsgType.NO_SUCH_TRAVEL_COURSE)
                        .build();
            }

            travelCourses.setTravelTitle(requestDto.getTitle());
            travelCourses.setStartDate(requestDto.getStartDate());
            travelCourses.setEndDate(requestDto.getEndDate());
            travelCourses.setTravelDays(requestDto.getTotalDays());

            travelCourses.setTravelCourseSchedules(null);
            List<TravelCourseSchedule> scheduleList = new ArrayList<>();
            for(int i = 0; i < requestDto.getTotalDays(); i++){
                for(int j = 0; j < requestDto.getCourseInfo().get(i).size(); j++){
                    TravelCourseSchedule schedule = TravelCourseSchedule.builder()
                            .travelCourse(travelCourses)
                            .name(requestDto.getCourseInfo().get(i).get(j).getName())
                            .category(requestDto.getCourseInfo().get(i).get(j).getCategory())
                            .address(requestDto.getCourseInfo().get(i).get(j).getAddress())
                            .memo(requestDto.getCourseInfo().get(i).get(j).getMemo())
                            .image(requestDto.getCourseInfo().get(i).get(j).getImg())
                            .idx(j)
                            .day(i)
                            .latitude(requestDto.getCourseInfo().get(i).get(j).getLat())
                            .longitude(requestDto.getCourseInfo().get(i).get(j).getLng())
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

    public ServiceControllerDataDto<Object> courseDelete(Long travelCourseId, HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            TravelCourses travelCourses = travelCoursesRepository.findTravelCoursesByIdAndDeletedDateIsNull(travelCourseId).orElse(null);
            if(travelCourses == null){
                return ServiceControllerDataDto.builder()
                        .data(false)
                        .msg(MsgType.NO_SUCH_TRAVEL_COURSE)
                        .build();
            }

            travelCourses.setDeletedDate(LocalDateTime.now());
            travelCoursesRepository.save(travelCourses);

            return ServiceControllerDataDto.builder()
                    .data(true)
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
                .img(schedule.getImage())
                .lat(String.valueOf(schedule.getLatitude()))
                .lng(String.valueOf(schedule.getLongitude()))
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