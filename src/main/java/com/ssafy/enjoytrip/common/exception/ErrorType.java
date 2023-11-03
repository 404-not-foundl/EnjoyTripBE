package com.ssafy.enjoytrip.common.exception;

import lombok.Getter;

@Getter
public enum ErrorType {

    ALREADY_EXISTED_USERID(401, "이미 존재하는 아이디입니다."),
    NOT_EXISTED_USERID(401, "존재하지 않는 아이디입니다."),
    PASSWORD_NOT_MATCH_USERID(401, "비밀번호가 일치하지 않습니다.");

    private int code;
    private String msg;

    ErrorType(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
