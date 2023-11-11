package com.ssafy.enjoytrip.board.dto.request;

import lombok.Getter;

@Getter
public class BoardQnaUpdateArticleRequestDto {

    private Long articleId;
    private String userNick;
    private String title;
    private String content;
}
