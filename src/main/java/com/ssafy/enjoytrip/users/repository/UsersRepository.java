package com.ssafy.enjoytrip.users.repository;

import com.ssafy.enjoytrip.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    Optional<Users> findById(Long id);
    Optional<Users> findByUserLoginIdAndDeletedDateIsNull(String userLogId);
    Optional<Users> findByUserNicknameAndDeletedDateIsNull(String userNick);
    Optional<Users> findByUserLoginIdAndUserPasswordQuestionAndDeletedDateIsNull(String userLoginId, String userPasswordQuestion);
}
