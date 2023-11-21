package com.ssafy.enjoytrip.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ssafy.enjoytrip.common.exception.ErrorResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "API response")
public class ApiResponseDto<T> {

    @ApiModelProperty(value = "Response data")
    private T data;

    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ErrorResponse error;
}
