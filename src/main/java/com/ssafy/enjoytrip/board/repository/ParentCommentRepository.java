package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.entity.community.ParentComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentCommentRepository extends JpaRepository<ParentComment, Long> {
}
