package com.ssafy.enjoytrip.board.dto.request;

import lombok.Getter;

@Getter
public class BoardQnaListOfArticleRequestDto {

    private String searchCategory;
    private String searchWord;
    private int pageToMove;
    private int shownArticleNum;

}
