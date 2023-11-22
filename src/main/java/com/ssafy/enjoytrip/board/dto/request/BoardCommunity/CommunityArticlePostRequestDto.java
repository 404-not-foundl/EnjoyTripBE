package com.ssafy.enjoytrip.board.dto.request.BoardCommunity;

import lombok.Getter;

import javax.persistence.Lob;

@Getter
public class CommunityArticlePostRequestDto {

    private String title;

    @Lob
    private String content;
}
