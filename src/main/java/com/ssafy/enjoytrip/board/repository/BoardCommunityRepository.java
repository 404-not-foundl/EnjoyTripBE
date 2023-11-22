package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.entity.community.BoardCommunityArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCommunityRepository extends JpaRepository<BoardCommunityArticle, Long> {
    Page<BoardCommunityArticle> findAllByDeletedDateIsNull();
}
