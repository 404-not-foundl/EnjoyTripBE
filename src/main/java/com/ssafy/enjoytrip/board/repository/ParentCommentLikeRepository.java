package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.entity.community.ParentCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentCommentLikeRepository extends JpaRepository<ParentCommentLike, Long> {
}
