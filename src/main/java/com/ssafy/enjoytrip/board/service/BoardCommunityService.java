package com.ssafy.enjoytrip.board.service;

import com.ssafy.enjoytrip.board.dto.request.BoardCommunity.CommunityArticlePostRequestDto;
import com.ssafy.enjoytrip.board.entity.community.BoardCommunityArticle;
import com.ssafy.enjoytrip.board.repository.BoardCommunityRepository;
import com.ssafy.enjoytrip.common.exception.CustomException;
import com.ssafy.enjoytrip.common.exception.ErrorType;
import com.ssafy.enjoytrip.common.response.ApiResponseDto;
import com.ssafy.enjoytrip.common.response.MsgType;
import com.ssafy.enjoytrip.common.response.ServiceControllerDataDto;
import com.ssafy.enjoytrip.common.security.CookieUserCheck;
import com.ssafy.enjoytrip.users.entity.Users;
import com.ssafy.enjoytrip.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardCommunityService {

    private final BoardCommunityRepository boardCommunityRepository;
    private final UsersRepository usersRepository;

    public ServiceControllerDataDto<Object> communityArticleList(int pageToMove, int shownArticleNum,HttpServletRequest request){
        CookieUserCheck cookieUserCheck = checkFilter(request);
        if(cookieUserCheck.getMsg() != null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(cookieUserCheck.getMsg())
                    .build();
        }
        Users user = cookieUserCheck.getUser();

        Pageable pageable = PageRequest.of(pageToMove-1, shownArticleNum, Sort.by(Sort.Order.desc("articleId")));
        Page<BoardCommunityArticle> page;

        return null;
    }

    public ServiceControllerDataDto<Object> communityArticlePost(CommunityArticlePostRequestDto requestDto, HttpServletRequest request){
        CookieUserCheck cookieUserCheck = checkFilter(request);
        if(cookieUserCheck.getMsg() != null){
            return ServiceControllerDataDto.builder()
                    .data(false)
                    .msg(cookieUserCheck.getMsg())
                    .build();
        }
        Users user = cookieUserCheck.getUser();

        BoardCommunityArticle boardCommunityArticle = BoardCommunityArticle.builder()
                .user(user)
                .communityBoardTitle(requestDto.getTitle())
                .communityBoardContent(requestDto.getContent())
                .build();

        boardCommunityRepository.save(boardCommunityArticle);

        return ServiceControllerDataDto.builder()
                .data(true)
                .msg(MsgType.COMMUNITY_BOARD_SAVE_COMPLETE)
                .build();
    }

    private CookieUserCheck checkFilter(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String userLoginId = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userId")){
                    userLoginId = cookie.getValue();
                }
            }
        }
        if(userLoginId == null){
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
        Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userLoginId).orElse(new Users());
        if(user.getId() == null){
            throw new CustomException(ErrorType.NOT_FOUND_USER);
        }
        return CookieUserCheck.builder()
                .user(user)
                .build();
    }
}
