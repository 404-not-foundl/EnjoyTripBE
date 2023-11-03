package com.ssafy.enjoytrip.common.jwt.repository;

import com.ssafy.enjoytrip.common.jwt.token.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByAccessToken(String accessToken);
}
