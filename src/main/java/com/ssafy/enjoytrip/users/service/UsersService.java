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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    @Value("${upload.directory.userImage}")
    private String uploadDirUserImg;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final UsersRepository usersRepository;

    @Transactional
    public MsgType join(JoinRequestDto requestDto){
        String encodedPwd = passwordEncoder.encode(requestDto.getUserPwd());

        Users user = Users.builder()
                .userLoginId(requestDto.getUserLogId())
                .userPassword(encodedPwd)
                .userNickname(requestDto.getUserNick())
                .userNationality(requestDto.getUserNation())
                .userPhoneNumber(requestDto.getUserPhone())
                .userEmail(requestDto.getUserEmail())
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

//    public Boolean findPassword(FindPasswordRequestDto requestDto){
//        Optional<Users> usersOptional = usersRepository.findByUserLoginIdAndUserPasswordQuestionAndDeletedDateIsNull(requestDto.getUserLogId(), requestDto.getUserPwdQue());
//        if(usersOptional.isPresent()) {
//            Users user = usersOptional.get();
//            user.setUserPassword(passwordEncoder.encode(requestDto.getNewPassword()));
//            usersRepository.save(user);
//        }
//        return usersOptional.isPresent();
//    }

    public Boolean login(LoginRequestDto requestDto, HttpServletResponse response){
        Optional<Users> usersOptional = usersRepository.findByUserLoginIdAndDeletedDateIsNull(requestDto.getUserLogId());
        if(usersOptional.isPresent() && passwordEncoder.matches(requestDto.getUserPwd(), usersOptional.get().getUserPassword())){
            CookieGenerator cg = new CookieGenerator();
            cg.setCookieName("userId");
            cg.setCookieMaxAge(3600);
            cg.setCookieHttpOnly(true);
            cg.addCookie(response, usersOptional.get().getUserLoginId());
            return true;
        }else return false;
    }

    public MsgType logout(HttpServletResponse response){
        CookieGenerator cg = new CookieGenerator();
        cg.setCookieName("userId");
        cg.setCookieMaxAge(0);
        cg.addCookie(response, "");
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
                              .userNation(optionalUsers.get().getUserNationality())
                              .userEmail(optionalUsers.get().getUserEmail())
                              .userProfileImage(optionalUsers.get().getUserProfileImage())
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

    public MsgType profileImage(MultipartFile userImage, HttpServletRequest request){

        if(userImage.isEmpty()) return MsgType.NO_IMAGE_SENT;
        if(checkCookieUserId(request) == null) return MsgType.NO_COOKIE_FOUND;
        String userLoginId = checkCookieUserId(request).getValue();
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() == null) return MsgType.USER_NOT_FOUND;
        String fileName = "user_" + user.getId() + "_profile";
        try{
            Path filePath = Path.of(uploadDirUserImg, fileName);
            Files.copy(userImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            user.setUserProfileImage(fileName);
            usersRepository.save(user);
        }catch (IOException e){
            return MsgType.FILE_UPLOAD_FAIL;
        }

        return MsgType.USER_IMAGE_UPLOAD_COMPLETE;
    }

    public MsgType deleteProfileImage(HttpServletRequest request){

        if(checkCookieUserId(request) == null) return MsgType.NO_COOKIE_FOUND;
        String userLoginId = checkCookieUserId(request).getValue();
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() == null) return MsgType.USER_NOT_FOUND;
        String fileName = "user_" + user.getId() + "_profile";
        Path filePath = Path.of(uploadDirUserImg, fileName);

        try{
            Files.deleteIfExists(filePath);
            user.setUserProfileImage(null);
            usersRepository.save(user);
        }catch (IOException e){
            return MsgType.NO_FILE_EXIST;
        }

         return MsgType.FILE_DELETE_COMPLETE;
    }

    public Resource getImage(String filename){
        try{
            Path filePath = Paths.get(uploadDirUserImg, filename);
            byte[] imageBytes = Files.readAllBytes(filePath);
            return new ByteArrayResource(imageBytes);
        }catch (IOException e){
            return null;
        }
    }

    public Cookie checkCookieUserId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();

        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userId")){
                    return cookie;
                }
            }
        }

        return null;
    }
}
