package com.ssafy.enjoytrip.travelCourses.service;

import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import com.ssafy.enjoytrip.travelCourses.dto.requestDto.TravelLike.TravelLikeSaveRequestDto;
import com.ssafy.enjoytrip.travelCourses.dto.responseDto.TravelLike.TravelLikeListResponseDto;
import com.ssafy.enjoytrip.travelCourses.entity.TravelLike.TravelLike;
import com.ssafy.enjoytrip.travelCourses.repository.TravelLike.TravelLikeRepository;
import com.ssafy.enjoytrip.users.entity.Users;
import com.ssafy.enjoytrip.users.repository.UsersRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelLikeService {

    private final TravelLikeRepository travelLikeRepository;
    private final UsersRepository usersRepository;

    public ServiceControllerDataDto<Object> likeSave(TravelLikeSaveRequestDto requestDto, HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() != null){
            TravelLike DBTravelLike = travelLikeRepository.findByNameAndAddressAndCategoryAndLatitudeAndLongitudeAndUser(requestDto.getName(), requestDto.getAddress(), requestDto.getCategory(), requestDto.getLat(), requestDto.getLng(), user);
            if(DBTravelLike != null){
                return ServiceControllerDataDto.builder()
                        .data(false)
                        .msg(MsgType.TRAVEL_LIKE_SAVE_FAIL)
                        .build();
            }

            TravelLike travelLike = TravelLike.builder()
                    .user(user)
                    .contentId(requestDto.getContentId())
                    .contentTypeId(requestDto.getContentTypeId())
                    .name(requestDto.getName())
                    .category(requestDto.getCategory())
                    .address(requestDto.getAddress())
                    .image(requestDto.getImg())
                    .latitude(requestDto.getLat())
                    .longitude(requestDto.getLng())
                    .build();
            travelLikeRepository.save(travelLike);

            return ServiceControllerDataDto.builder()
                    .data(true)
                    .msg(MsgType.TRAVEL_LIKE_SAVE_COMPLETE)
                    .build();
        }

        return ServiceControllerDataDto.builder()
                .data(false)
                .msg(MsgType.USER_NOT_FOUND)
                .build();
    }

    public ServiceControllerDataDto<Object> likeList(HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.USER_NOT_FOUND)
                    .build();
        }

        List<TravelLike> travelLikeList = travelLikeRepository.findAllByUser(user);
        List<TravelLikeListResponseDto> travelLikeListResponseDtoList = new ArrayList<>();
        for(TravelLike travelLike : travelLikeList){
            TravelLikeListResponseDto travelLikeListResponseDto = TravelLikeListResponseDto.builder()
                    .contentId(travelLike.getContentId())
                    .contentTypeId(travelLike.getContentTypeId())
                    .name(travelLike.getName())
                    .category(travelLike.getCategory())
                    .address(travelLike.getAddress())
                    .img(travelLike.getImage())
                    .lat(travelLike.getLatitude())
                    .lng(travelLike.getLongitude())
                    .build();
            travelLikeListResponseDtoList.add(travelLikeListResponseDto);
        }

        return ServiceControllerDataDto.builder()
                .data(travelLikeListResponseDtoList)
                .msg(MsgType.TRAVEL_LIKE_LIST_SENT)
                .build();
    }

    public ServiceControllerDataDto<Object> likeDelete(String contentId, String contentTypeId, HttpServletRequest request){
        String userLoginId = checkCookieUserId(request).getValue();
        if(userLoginId == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.NO_COOKIE_FOUND)
                    .build();
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.USER_NOT_FOUND)
                    .build();
        }
        TravelLike travelLike = travelLikeRepository.findByUserAndContentIdAndContentTypeId(user, contentId, contentTypeId);
        if(travelLike == null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(MsgType.TRAVEL_LIKE_NOT_FOUND)
                    .build();
        }

        travelLikeRepository.delete(travelLike);
        return ServiceControllerDataDto.builder()
                .data(true)
                .msg(MsgType.TRAVEL_LIKE_DELETE_COMPLETE)
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