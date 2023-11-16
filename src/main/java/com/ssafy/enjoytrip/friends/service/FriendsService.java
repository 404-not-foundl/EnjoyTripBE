package com.ssafy.enjoytrip.friends.service;

import com.ssafy.enjoytrip.friends.repository.FriendListRepository;
import com.ssafy.enjoytrip.friends.repository.FriendRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FriendsService {

    private final FriendListRepository friendListRepository;
    private final FriendRequestRepository friendRequestRepository;


}
