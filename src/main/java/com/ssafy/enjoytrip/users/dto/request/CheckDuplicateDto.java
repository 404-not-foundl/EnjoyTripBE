package com.ssafy.enjoytrip.users.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CheckDuplicateDto {

    @NotBlank
    private String duplicate;
}
