package com.ssafy.enjoytrip.users.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class JoinRequestDto {

    @NotBlank
    private String userLogId;
    @NotBlank
    private String userPwd;
    @NotBlank
    private String userNick;
    @NotBlank
    private String userNation;
    @NotBlank
    private String userPhone;
    @NotBlank
    private String userEmail;
}
