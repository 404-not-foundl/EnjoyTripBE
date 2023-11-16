package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.entity.qna.BoardQnaArticle;
import com.ssafy.enjoytrip.board.entity.qna.BoardQnaListArticleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardQnaRepository extends JpaRepository<BoardQnaArticle, Long> {
    Optional<BoardQnaArticle> findByArticleIdAndDeletedDateIsNull(Long articleId);

    Page<BoardQnaListArticleProjection> findAllByDeletedDateIsNull(Pageable pageable);
    Page<BoardQnaListArticleProjection> findByArticleIdAndDeletedDateIsNull(Long articleId, Pageable pageable);
    Page<BoardQnaListArticleProjection> findByTitleContainingAndDeletedDateIsNull(String title, Pageable pageable);
    Page<BoardQnaListArticleProjection> findByUserNickContainingAndDeletedDateIsNull(String userNick, Pageable pageable);
}
