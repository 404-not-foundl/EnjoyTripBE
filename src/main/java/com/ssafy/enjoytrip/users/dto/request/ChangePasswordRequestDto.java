package com.ssafy.enjoytrip.users.dto.request;

import lombok.Getter;

@Getter
public class ChangePasswordRequestDto {
    private String userLogId;
    private String originalPassword;
    private String newPassword;
}
