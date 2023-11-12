package com.ssafy.enjoytrip.common.response;

import lombok.Getter;

@Getter
public enum MsgType {

    JOIN_SUCCESSFULLY("회원가입 성공"),
    JOIN_FAIL("회원가입 실패"),
    LOGIN_SUCCESSFULLY("로그인 성공"),
    LOGOUT_SUCCESSFULLY("로그아웃 성공"),
    CHECK_ID_NO_DUPLICATE("아이디 중복 없음"),
    CHECK_ID_YES_DUPLICATE("아이디 중복 있음"),
    NO_SUCH_USER("회원이 존재하지 않음"),
    PASSWORD_CHANGE_COMPLETE("비밀번호 변경 완료"),
    USER_DELETED_COMPLETE("계정삭제 완료"),
    USER_NOT_FOUND("유저가 존재하지 않습니다."),
    USER_NOT_MATCH("유저가 일치하지 않습니다."),
    NO_COOKIE_FOUND("쿠키가 존재하지 않습니다."),
    QNA_ARTICLE_SAVE_COMPLETE("Qna Article 저장 완료"),
    QNA_ARTICLE_UPDATE_COMPLETE("Qna Article 업데이트 완료"),
    QNA_ARTICLE_DELETE_COMPLETE("Qna Article 삭제 완료"),
    QNA_ARTICLE_DOES_NOT_EXIST("Qna Article이 존재하지 않습니다."),
    TRAVEL_COURSE_LIST_SAVE_COMPLETE("여행 일정 저장 완료");


    private final String msg;

    MsgType(String msg){
        this.msg = msg;
    }
}
