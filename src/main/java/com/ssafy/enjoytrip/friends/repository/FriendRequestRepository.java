package com.ssafy.enjoytrip.friends.repository;

import com.ssafy.enjoytrip.friends.entity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
}
