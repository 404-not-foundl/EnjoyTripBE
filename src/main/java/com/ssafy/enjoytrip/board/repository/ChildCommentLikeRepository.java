package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.entity.community.ChildCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildCommentLikeRepository extends JpaRepository<ChildCommentLike, Long> {
}
