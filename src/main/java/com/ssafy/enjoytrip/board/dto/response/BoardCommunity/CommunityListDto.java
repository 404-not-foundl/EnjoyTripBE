package com.ssafy.enjoytrip.board.dto.response.BoardCommunity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter@Builder
public class CommunityListDto {

    private Long id;
    private String userNickname;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime updatedDate;
}
