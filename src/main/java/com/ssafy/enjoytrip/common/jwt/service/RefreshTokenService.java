package com.ssafy.enjoytrip.common.jwt.service;

import com.ssafy.enjoytrip.common.jwt.repository.RefreshTokenRepository;
import com.ssafy.enjoytrip.common.jwt.token.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public void saveTokenInfo(Long userId, String refreshToken, String accessToken){
        refreshTokenRepository.save(new RefreshToken(String.valueOf(userId), refreshToken, accessToken));
    }

    @Transactional
    public void removeRefreshToken(String accessToken){
        refreshTokenRepository.findByAccessToken(accessToken)
                .ifPresent(refreshTokenRepository::delete);
    }
}
