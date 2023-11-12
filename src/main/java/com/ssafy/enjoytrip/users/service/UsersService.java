package com.ssafy.enjoytrip.users.service;

import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.users.dto.request.CheckDuplicateDto;
import com.ssafy.enjoytrip.users.dto.request.FindPasswordRequestDto;
import com.ssafy.enjoytrip.users.dto.request.JoinRequestDto;
import com.ssafy.enjoytrip.users.dto.request.LoginRequestDto;
import com.ssafy.enjoytrip.users.dto.response.UserInfoDto;
import com.ssafy.enjoytrip.users.entity.Users;
import com.ssafy.enjoytrip.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final UsersRepository usersRepository;

    @Transactional
    public MsgType join(JoinRequestDto requestDto){
        String encodedPwd = passwordEncoder.encode(requestDto.getUserPwd());

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

    public Boolean findPassword(FindPasswordRequestDto requestDto){
        Optional<Users> usersOptional = usersRepository.findByUserLoginIdAndUserPasswordQuestionAndDeletedDateIsNull(requestDto.getUserLogId(), requestDto.getUserPwdQue());
        if(usersOptional.isPresent()) {
            Users user = usersOptional.get();
            user.setUserPassword(passwordEncoder.encode(requestDto.getNewPassword()));
            usersRepository.save(user);
        }
        return usersOptional.isPresent();
    }

    public Boolean login(LoginRequestDto requestDto, HttpServletResponse response){
        Optional<Users> usersOptional = usersRepository.findByUserLoginIdAndDeletedDateIsNull(requestDto.getUserLogId());
        if(usersOptional.isPresent() && passwordEncoder.matches(requestDto.getUserPwd(), usersOptional.get().getUserPassword())){
            CookieGenerator cg = new CookieGenerator();
            cg.setCookieName("userId");
            cg.setCookieMaxAge(3600);
            cg.addCookie(response, usersOptional.get().getUserLoginId());
            return true;
        }else return false;
    }

    public MsgType logout(HttpServletResponse response){
        Cookie deleteCookie = new Cookie("userId", null);
        deleteCookie.setMaxAge(0);
        response.addCookie(deleteCookie);
        return MsgType.LOGOUT_SUCCESSFULLY;
    }

    public UserInfoDto userInfo(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String userLoginId;

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userId")){
                    userLoginId = cookie.getValue();
                    Optional<Users> optionalUsers = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId);
                    if(optionalUsers.isPresent()){
                      return UserInfoDto.builder()
                              .userLogId(optionalUsers.get().getUserLoginId())
                              .userNick(optionalUsers.get().getUserNickname())
                              .build();
                    }
                    break;
                }
            }
        }

        return null;
    }

    public void deleteUser(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String userLoginId;

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userId")){
                    userLoginId = cookie.getValue();
                    Optional<Users> optionalUsers = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId);
                    if(optionalUsers.isPresent()){
                        Users user = optionalUsers.get();
                        user.setDeletedDate(LocalDateTime.now());
                        usersRepository.save(user);
                    }
                    break;
                }
            }
        }
    }
}
