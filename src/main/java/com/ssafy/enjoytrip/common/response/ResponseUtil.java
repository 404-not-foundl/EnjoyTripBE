package com.ssafy.enjoytrip.common.response;

import com.ssafy.enjoytrip.common.exception.ErrorResponse;

public class ResponseUtil {

    public static <T> ApiResponseDto<T> ok(T data){
        return ApiResponseDto.<T>builder()
                .data(data)
                .build();
    }

    public static <T> ApiResponseDto<T> ok(T data, MsgType msg){
        return ApiResponseDto.<T>builder()
                .data(data)
                .msg(msg.getMsg())
                .build();
    }

    public static ApiResponseDto<Void> ok(MsgType msg){
        return ApiResponseDto.<Void>builder()
                .msg(msg.getMsg())
                .build();
    }

    public static ApiResponseDto<Void> error(ErrorResponse error){
        return ApiResponseDto.<Void>builder()
                .error(error)
                .build();
    }
}
