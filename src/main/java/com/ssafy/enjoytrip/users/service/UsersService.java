package com.ssafy.enjoytrip.users.service;

import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.users.dto.request.CheckDuplicateDto;
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
    public MsgType join(JoinRequestDto requestDto){
        String encodedPwd = BCrypt.hashpw(requestDto.getUserPwd(), BCrypt.gensalt());

        Users user = Users.builder()
                .userLoginId(requestDto.getUserLogId())
                .userPassword(encodedPwd)
                .userNickname(requestDto.getUserNick())
                .userPasswordQuestion(requestDto.getUserPwdQue())
                .build();

        usersRepository.save(user);

        return MsgType.JOIN_SUCCESSFULLY;
    }

    public boolean checkId(CheckDuplicateDto requestDto){
        return usersRepository.findByUserLoginIdAndDeletedDateIsNull(requestDto.getDuplicate()).isPresent();
    }

    public boolean checkNickname(CheckDuplicateDto requestDto){
        return usersRepository.findByUserNicknameAndDeletedDateIsNull(requestDto.getDuplicate()).isPresent();
    }
}
