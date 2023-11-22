package com.ssafy.enjoytrip.common.security;

import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
public class CookieUserCheck {

    private MsgType msg;

    private Users user;
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