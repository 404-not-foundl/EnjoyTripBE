package com.ssafy.enjoytrip.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.enjoytrip.common.exception.ErrorResponse;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {

    private T data;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorResponse error;
}
