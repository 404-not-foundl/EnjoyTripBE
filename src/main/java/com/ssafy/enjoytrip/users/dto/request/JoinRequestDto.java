package com.ssafy.enjoytrip.users.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class JoinRequestDto {

    @NotBlank
    private String userLoginId;

    @NotBlank
    private String userPassword;
}
