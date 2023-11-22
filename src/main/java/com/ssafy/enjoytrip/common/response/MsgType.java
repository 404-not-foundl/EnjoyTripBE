package com.ssafy.enjoytrip.common.response;

import lombok.Getter;

@Getter
public enum MsgType {

    // USER
    JOIN_SUCCESSFULLY("회원가입 성공"),
    JOIN_FAIL("회원가입 실패"),
    LOGIN_SUCCESSFULLY("로그인 성공"),
    LOGOUT_SUCCESSFULLY("로그아웃 성공"),
    CHECK_ID_NO_DUPLICATE("아이디 중복 없음"),
    CHECK_ID_YES_DUPLICATE("아이디 중복 있음"),
    NO_SUCH_USER("회원이 존재하지 않음"),
    WRONG_USER("올바르지 않은 회원입니다."),
    WRONG_PASSWORD("비밀번호가 옳지 않습니다."),
    PASSWORD_CHANGE_COMPLETE("비밀번호 변경 완료"),
    USER_DELETED_COMPLETE("계정삭제 완료"),
    USER_NOT_FOUND("유저가 존재하지 않습니다."),
    USER_NOT_MATCH("유저가 일치하지 않습니다."),
    NO_COOKIE_FOUND("쿠키가 존재하지 않습니다."),

    // USER IMAGE
    USER_IMAGE_UPLOAD_COMPLETE("유저 이미지 업로드 완료"),
    NO_IMAGE_SENT("이미지가 전달되지 않았습니다."),

    // QNA ARTICLE
    QNA_ARTICLE_SAVE_COMPLETE("Qna Article 저장 완료"),
    QNA_ARTICLE_UPDATE_COMPLETE("Qna Article 업데이트 완료"),
    QNA_ARTICLE_DELETE_COMPLETE("Qna Article 삭제 완료"),
    QNA_ARTICLE_DOES_NOT_EXIST("Qna Article이 존재하지 않습니다."),
    TRAVEL_COURSE_LIST_SAVE_COMPLETE("여행 일정 저장 완료"),

    // TRAVEL COURSE
    TRAVEL_COURSE_SAVE_COMPLETE("여행 일정 저장 완료"),
    NO_SUCH_TRAVEL_COURSE("해당 여행 일정이 존재하지 않습니다."),
    TRAVEL_COURSE_INFO_SEND_COMPLETE("여행 일정 전송이 완료 되었습니다."),
    TRAVEL_COURSE_UPDATE_COMPLETE("여행 일정 업데이트 완료."),
    TRAVEL_COURSE_DELETE_COMPLETE("여행 일정 삭제 완료"),
    TRAVEL_COURSE_LIST_COMPLETE("여행 일정 목록 전송 완료"),

    // TRAVEL LIKE
    TRAVEL_LIKE_SAVE_COMPLETE("좋아요 저장 완료"),
    TRAVEL_LIKE_SAVE_FAIL("이미 좋아요를 누른 여행지입니다."),
    TRAVEL_LIKE_LIST_SENT("좋아요 리스트 전송 완료"),
    TRAVEL_LIKE_NOT_FOUND("좋아요를 찾지 못하였습니다."),
    TRAVEL_LIKE_DELETE_COMPLETE("좋아요 삭제 완료"),

    // COMMUNITY BOARD
    COMMUNITY_BOARD_SAVE_COMPLETE("자유게시판 저장 완료"),

    // FILES
    NO_FILE_EXIST("파일이 존재하지 않습니다."),
    FILE_UPLOAD_FAIL("파일 업로드에 실패하였습니다."),
    FILE_DELETE_COMPLETE("파일 삭제를 완료하였습니다.");


    private final String msg;

    MsgType(String msg){
        this.msg = msg;
    }
}
