package com.ssafy.enjoytrip.board.entity;

import java.time.LocalDateTime;

public interface BoardQnaListArticleProjection {

    Long getArticleId();
    String getUserNick();
    String getTitle();
    int getHit();
    LocalDateTime getUpdatedDate();

}
