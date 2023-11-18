package com.ssafy.enjoytrip.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter@Setter
public class ServiceControllerDataDto<T> {

    private T data;
    private MsgType msg;
}
