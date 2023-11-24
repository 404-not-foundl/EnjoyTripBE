package com.ssafy.enjoytrip.board.repository;

import com.ssafy.enjoytrip.board.dto.response.BoardCommunity.CommunityListDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomCommunityBoardRepository {

    @PersistenceContext
    private EntityManager entityManager;

}
