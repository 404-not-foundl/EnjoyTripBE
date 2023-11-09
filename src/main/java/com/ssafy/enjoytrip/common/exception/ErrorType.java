package com.ssafy.enjoytrip.common.exception;

import lombok.Getter;

@Getter
public enum ErrorType {

    ALREADY_EXISTED_USERID(401, "이미 존재하는 아이디입니다."),
    NOT_EXISTED_USERID(401, "존재하지 않는 아이디입니다."),
    NOT_TOKEN(401, "토큰이 없습니다."),
    NOT_VALID_TOKEN(401, "토큰이 유효하지 않습니다."),
    NOT_FOUND_DIARY(401, "등록된 가계부가 없습니다."),
    NOT_FOUND_USER(401,"유저를 찾지 못했습니다."),
    PASSWORD_NOT_MATCH_USERID(401, "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED_USER_ACCESS(401, "유효하지 않은 접근자 제한");

    private int code;
    private String msg;

    ErrorType(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
