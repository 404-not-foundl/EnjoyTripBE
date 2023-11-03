package com.ssafy.enjoytrip.common.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ErrorResponse {

    private int status;
    private String msg;

    public static ErrorResponse of(ErrorType errorType){
        return ErrorResponse.builder()
                .status(errorType.getCode())
                .msg(errorType.getMsg())
                .build();
    }
}
