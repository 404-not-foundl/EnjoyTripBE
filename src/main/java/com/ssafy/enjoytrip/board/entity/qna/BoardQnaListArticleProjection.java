package com.ssafy.enjoytrip.board.entity.qna;

import java.time.LocalDateTime;

public interface BoardQnaListArticleProjection {

    Long getArticleId();
    String getUserNick();
    String getTitle();
    int getHit();
    LocalDateTime getUpdatedDate();

}
