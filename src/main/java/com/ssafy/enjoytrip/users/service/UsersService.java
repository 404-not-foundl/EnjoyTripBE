package com.ssafy.enjoytrip.users.service;

import com.ssafy.enjoytrip.common.address.DomainName;
import com.ssafy.enjoytrip.common.address.image.ProfileImageDomain;
import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.users.dto.request.*;
import com.ssafy.enjoytrip.users.dto.response.CacheImageToProfileImageResponseDto;
import com.ssafy.enjoytrip.users.dto.response.CacheImageUpdateResponseDto;
import com.ssafy.enjoytrip.users.dto.response.UserInfoDto;
import com.ssafy.enjoytrip.users.entity.Users;
import com.ssafy.enjoytrip.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {

    @Value("${upload.directory.userImage}")
    private String uploadDirUserImg;
    @Value("${upload.directory.cacheUserImage}")
    private String uploadDirUserImgCache;
    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final UsersRepository usersRepository;
    private final ResourceLoader resourceLoader;

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

    public Boolean login(LoginRequestDto requestDto,HttpServletRequest request, HttpServletResponse response){
        if(checkCookieUserId(request) != null) return false;
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
                                .userPhone(optionalUsers.get().getUserPhoneNumber())
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

    public MsgType changePassword(ChangePasswordRequestDto requestDto, HttpServletRequest request){
        if(checkCookieUserId(request) == null) return MsgType.NO_COOKIE_FOUND;
        String userLoginId = checkCookieUserId(request).getValue();
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(null);
        if(user == null) return MsgType.USER_NOT_FOUND;
        if(!user.getUserLoginId().equals(requestDto.getUserLogId())) return MsgType.WRONG_USER;
        if(!passwordEncoder.matches(requestDto.getOriginalPassword(), user.getUserPassword())) return MsgType.WRONG_PASSWORD;
        user.setUserPassword(passwordEncoder.encode(requestDto.getNewPassword()));
        usersRepository.save(user);
        return MsgType.PASSWORD_CHANGE_COMPLETE;
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
            user.setUserProfileImage(DomainName.DOMAIN_NAME.getDomain()+ ProfileImageDomain.PROFILE_IMAGE_DOMAIN.getDomain()+fileName);
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

    public boolean clearCacheImage(HttpServletRequest request){
        if(checkCookieUserId(request) == null) return false;
        String userLoginId = checkCookieUserId(request).getValue();
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() == null) return false;
        String fileName = "user_" + user.getId() + "_profile";
        if(user.getUserProfileImage() == null) {
            String extendedFileName = doesFileExist(uploadDirUserImgCache, fileName);
            if(extendedFileName != null){
                try {
                    Files.deleteIfExists(Path.of(uploadDirUserImgCache, extendedFileName));
                }catch (IOException e){
                    return true;
                }
            }
            return true;
        }
        try{
            String extendedFileName = doesFileExist(uploadDirUserImg, fileName);
            Path sourceFilePath = Path.of(uploadDirUserImg, extendedFileName);
            Path destinationFilePath = Path.of(uploadDirUserImgCache, extendedFileName);
            Files.copy(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            return false;
        }

        return true;
    }

    public CacheImageUpdateResponseDto changeCacheImage(MultipartFile userImage, HttpServletRequest request){
        if(checkCookieUserId(request) != null){
            String userLoginId = Objects.requireNonNull(checkCookieUserId(request)).getValue();
            Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
            if(user.getId() != null){
                String fileName = doesFileExist(uploadDirUserImgCache, "user_" + user.getId() + "_profile");
                if(fileName != null){
                    try{
                        Files.deleteIfExists(Path.of(uploadDirUserImgCache, fileName));
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if(userImage == null){
                    return CacheImageUpdateResponseDto.builder().build();
                }
                fileName = "user_" + user.getId() + "_profile."+ Objects.requireNonNull(userImage.getOriginalFilename()).substring(userImage.getOriginalFilename().lastIndexOf(".") + 1);
                Path filePath = Path.of(uploadDirUserImgCache, fileName);
                try{
                    Files.createDirectories(filePath.getParent());
                    Files.copy(userImage.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                    String imageUrl = DomainName.DOMAIN_NAME.getDomain()+ ProfileImageDomain.CACHE_DOMAIN.getDomain()+fileName;
                    user.setUserProfileImage(imageUrl);
                    usersRepository.save(user);
                    return CacheImageUpdateResponseDto.builder()
                            .ImageUrl(imageUrl)
                            .build();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return CacheImageUpdateResponseDto.builder().build();
    }

    public CacheImageToProfileImageResponseDto cacheImgToProfileImg(HttpServletRequest request) {
        if (checkCookieUserId(request) != null) {
            String userLoginId = checkCookieUserId(request).getValue();
            Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
            if (user.getId() != null) {
                String destinationFileName = doesFileExist(uploadDirUserImg, "user_" + user.getId() + "_profile");
                if(destinationFileName != null){
                    try{
                        Files.deleteIfExists(Path.of(uploadDirUserImg, destinationFileName));
                        user.setUserProfileImage(null);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                String sourceFileName = doesFileExist(uploadDirUserImgCache, "user_" + user.getId() + "_profile");
                if(sourceFileName == null){
                    usersRepository.save(user);
                    return CacheImageToProfileImageResponseDto.builder().build();
                }
                Path destinationPath = Path.of(uploadDirUserImg, sourceFileName);
                Path sourcePath = Path.of(uploadDirUserImgCache, sourceFileName);
                try{
                    Files.createDirectories(destinationPath.getParent());
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    String imageUrl = DomainName.DOMAIN_NAME.getDomain()+ ProfileImageDomain.PROFILE_IMAGE_DOMAIN.getDomain()+sourceFileName;
                    user.setUserProfileImage(imageUrl);
                    usersRepository.save(user);
                    return CacheImageToProfileImageResponseDto.builder()
                            .ImageUrl(imageUrl)
                            .build();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return CacheImageToProfileImageResponseDto.builder().build();
    }

    private void deleteProfileImage(String fileNamePrefix) {
        String fileName = fileNamePrefix + "." + getFileExtension(Path.of(uploadDirUserImg, fileNamePrefix));
        Path profileFilePath = Path.of(uploadDirUserImg, fileName);

        try {
            Files.deleteIfExists(profileFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Cookie checkCookieUserId(HttpServletRequest request){
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

    private String getFileExtension(Path filePath) {
        String fileName = filePath.getFileName().toString();
        int lastDotIndex = fileName.lastIndexOf(".");

        return lastDotIndex == -1 ? null : fileName.substring(lastDotIndex + 1);
    }

    private Path findFileWithPrefix(Path directory, String fileNamePrefix) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directory, fileNamePrefix + "*")) {
            Iterator<Path> iterator = stream.iterator();
            return iterator.hasNext() ? iterator.next() : null;
        }
    }

    private String doesFileExist(String filePath, String fileName){
        try {
            String[] commonExtensions = {".jpeg", ".jpg", ".png"};
            for(String extension : commonExtensions){
                String extendedFilePath = filePath + "/" + fileName + extension;
                Resource resource = resourceLoader.getResource(extendedFilePath);
                if (resource.exists()) {
                    return fileName + extension;
                }
            }
            return null;
        }catch (Exception e) {
            return null;
        }
    }
}
