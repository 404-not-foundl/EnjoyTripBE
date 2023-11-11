package com.ssafy.enjoytrip.board.service;

import com.ssafy.enjoytrip.board.dto.request.*;
import com.ssafy.enjoytrip.board.dto.response.BoardQnaGetArticleResponseDto;
import com.ssafy.enjoytrip.board.dto.response.BoardQnaListOfArticleResponseDto;
import com.ssafy.enjoytrip.board.entity.BoardQnaArticle;
import com.ssafy.enjoytrip.board.entity.BoardQnaListArticleProjection;
import com.ssafy.enjoytrip.board.repository.BoardQnaRepository;
import com.ssafy.enjoytrip.common.response.MsgType;
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
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardQnaService {

    private final BoardQnaRepository boardQnaRepository;
    private final UsersRepository usersRepository;

    public MsgType saveArticle(BoardQnaSaveArticleRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie userIdCookie = checkCookieUserId(request);

        if(userIdCookie == null){
            response.sendRedirect("/login");
            return MsgType.USER_NOT_FOUND;
        }else {
            Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userIdCookie.getValue()).orElseGet(Users::new);

            BoardQnaArticle boardQnaArticle = BoardQnaArticle.builder()
                    .hit(0)
                    .title(requestDto.getTitle())
                    .content(requestDto.getContent())
                    .userNick(user.getUserNickname())
                    .build();

            boardQnaRepository.save(boardQnaArticle);

            return MsgType.QNA_ARTICLE_SAVE_COMPLETE;
        }
    }

    @Transactional
    public MsgType update(BoardQnaUpdateArticleRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie userIdCookie = checkCookieUserId(request);

        if (userIdCookie == null){
            response.sendRedirect("/login");
            return MsgType.USER_NOT_FOUND;
        }else{
            Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userIdCookie.getValue()).orElseGet(Users::new);

            if(!user.getUserNickname().equals(requestDto.getUserNick())){
                response.sendRedirect("/login");
                return MsgType.USER_NOT_MATCH;
            }else{
                BoardQnaArticle article = boardQnaRepository.findByArticleIdAndDeletedDateIsNull(requestDto.getArticleId()).orElseGet(BoardQnaArticle::new);
                if(article.getArticleId() == null){
                    return MsgType.QNA_ARTICLE_DOES_NOT_EXIST;
                }
                article.setTitle(requestDto.getTitle());
                article.setContent(requestDto.getContent());
                boardQnaRepository.save(article);

                return MsgType.QNA_ARTICLE_UPDATE_COMPLETE;
            }
        }
    }

    @Transactional
    public BoardQnaGetArticleResponseDto get(BoardQnaGetArticleRequestDto requestDto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie userIdCookie = checkCookieUserId(request);

        if(userIdCookie != null){
            BoardQnaArticle article = boardQnaRepository.findByArticleIdAndDeletedDateIsNull(requestDto.getArticleId()).orElseGet(BoardQnaArticle::new);
            if(article.getArticleId() == null) return null;
            article.setHit(article.getHit()+1);
            boardQnaRepository.save(article);
            return BoardQnaGetArticleResponseDto.builder()
                    .articleId(article.getArticleId())
                    .userNick(article.getUserNick())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .hit(article.getHit())
                    .updatedDate(article.getUpdatedDate())
                    .build();
        }

        return null;
    }

    public MsgType delete(BoardQnaDeleteArticleRequestDto requestDto, HttpServletRequest request, HttpServletResponse response){
        Cookie userIdCookie = checkCookieUserId(request);

        if(userIdCookie != null){
            Users user = usersRepository.findByUserLoginIdAndDeletedDateIsNull(userIdCookie.getValue()).orElseGet(Users::new);
            if(user.getUserNickname().equals(requestDto.getUserNick())){
                BoardQnaArticle article = boardQnaRepository.findByArticleIdAndDeletedDateIsNull(requestDto.getArticleId()).orElseGet(BoardQnaArticle::new);
                if(article.getArticleId() != null){
                    article.setDeletedDate(LocalDateTime.now());
                    boardQnaRepository.save(article);
                    return MsgType.QNA_ARTICLE_DELETE_COMPLETE;
                }else return MsgType.QNA_ARTICLE_DOES_NOT_EXIST;
            }
        }

        return MsgType.USER_NOT_MATCH;
    }

    public BoardQnaListOfArticleResponseDto list(BoardQnaListOfArticleRequestDto requestDto){
        Pageable pageable = PageRequest.of(requestDto.getPageToMove()-1, requestDto.getShownArticleNum(), Sort.by(Sort.Order.desc("articleId")));
        Page<BoardQnaListArticleProjection> page;
        if(requestDto.getSearchWord().isEmpty()){
            page = boardQnaRepository.findAllByDeletedDateIsNull(pageable);
            return BoardQnaListOfArticleResponseDto.builder()
                    .totalPage(page.getTotalPages())
                    .articleList(page)
                    .build();
        }else{
            if(requestDto.getSearchCategory().equals("articleNum")){
                Long articleId = null;
                try {
                    articleId = Long.parseLong(requestDto.getSearchWord());
                    page = boardQnaRepository.findByArticleIdAndDeletedDateIsNull(articleId, pageable);
                    return BoardQnaListOfArticleResponseDto.builder()
                            .totalPage(page.getTotalPages())
                            .articleList(page)
                            .build();
                }catch (NumberFormatException e){
                    return null;
                }
            } else if (requestDto.getSearchCategory().equals("title")) {
                page = boardQnaRepository.findByTitleContainingAndDeletedDateIsNull(requestDto.getSearchWord(), pageable);
                return BoardQnaListOfArticleResponseDto.builder()
                        .totalPage(page.getTotalPages())
                        .articleList(page)
                        .build();
            } else {
                page = boardQnaRepository.findByUserNickContainingAndDeletedDateIsNull(requestDto.getSearchWord(), pageable);
                return BoardQnaListOfArticleResponseDto.builder()
                        .totalPage(page.getTotalPages())
                        .articleList(page)
                        .build();
            }
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
