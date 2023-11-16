package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.entity.community.ParentComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCommentRepository extends JpaRepository<ParentComment, Long> {
}
