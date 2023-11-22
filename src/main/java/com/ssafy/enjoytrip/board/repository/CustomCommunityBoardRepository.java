package com.ssafy.enjoytrip.board.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomCommunityBoardRepository {

    @PersistenceContext
    private EntityManager entityManager;
}
