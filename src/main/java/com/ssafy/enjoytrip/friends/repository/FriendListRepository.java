package com.ssafy.enjoytrip.friends.repository;

import com.ssafy.enjoytrip.friends.entity.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long> {
}
