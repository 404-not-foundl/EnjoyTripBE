package com.ssafy.enjoytrip.users.dto.request;

import lombok.Getter;

@Getter
public class FindPasswordRequestDto {

    private String userLogId;
    private String userPwdQue;
    private String newPassword;
}
