package com.ssafy.enjoytrip.board.dto.response.BoardCommunity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter@Builder
public class CommunityListResponseDto {

    private List<CommunityListDto> communityList;
}
