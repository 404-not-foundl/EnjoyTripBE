package com.ssafy.enjoytrip.users.dto.response;

import com.ssafy.enjoytrip.users.entity.Users;
import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class UserInfoDto {
    private String userLogId;
    private String userNick;
}
