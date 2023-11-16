package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.entity.community.ChildComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildCommentRepository extends JpaRepository<ChildComment, Long> {
}
