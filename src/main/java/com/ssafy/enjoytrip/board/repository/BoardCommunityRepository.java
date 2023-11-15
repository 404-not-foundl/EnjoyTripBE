package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.entity.BoardCommunityArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCommunityRepository extends JpaRepository<BoardCommunityArticle, Long> {
}
