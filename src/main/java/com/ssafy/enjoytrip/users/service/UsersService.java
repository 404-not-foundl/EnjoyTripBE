package com.ssafy.enjoytrip.users.service;

import com.ssafy.enjoytrip.users.dto.request.JoinRequestDto;
import com.ssafy.enjoytrip.users.entity.Users;
import com.ssafy.enjoytrip.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UsersService {
    private UsersRepository usersRepository;

    @Transactional
    public void join(JoinRequestDto requestDto){
        String encodedPwd = BCrypt.hashpw(requestDto.getUserPassword(), BCrypt.gensalt());

        Users user = Users.builder()
                .userLoginId(requestDto.getUserLoginId())
                .userPassword(encodedPwd)
                .build();

        usersRepository.save(user);
    }
}
