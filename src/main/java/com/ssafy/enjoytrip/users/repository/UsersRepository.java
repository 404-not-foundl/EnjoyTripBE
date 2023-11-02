package com.ssafy.enjoytrip.users.repository;

import com.ssafy.enjoytrip.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
