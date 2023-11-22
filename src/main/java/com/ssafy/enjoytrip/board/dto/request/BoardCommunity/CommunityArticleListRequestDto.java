package com.ssafy.enjoytrip.board.dto.request.BoardCommunity;

import lombok.Getter;

@Getter
public class CommunityArticleListRequestDto {
    private int pageToMove;
    private int shownArticleNum;
    private String category;
    private String searchWord;
}
