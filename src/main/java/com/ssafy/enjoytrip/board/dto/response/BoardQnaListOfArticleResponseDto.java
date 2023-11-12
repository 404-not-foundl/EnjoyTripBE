package com.ssafy.enjoytrip.board.dto.response;

import com.ssafy.enjoytrip.board.entity.BoardQnaListArticleProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@Builder
public class BoardQnaListOfArticleResponseDto {

    private int totalPage;
    private List<BoardQnaListArticleProjection> articleList;
}
