package com.ssafy.enjoytrip.board.dto.response.BoardQna;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter @Getter
@Builder
public class BoardQnaGetArticleResponseDto {

    private Long articleId;
    private String userNick;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime updatedDate;
}
