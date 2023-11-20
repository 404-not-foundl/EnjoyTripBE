package com.ssafy.enjoytrip.travelCourses.repository.TravelLike;

import com.ssafy.enjoytrip.travelCourses.entity.TravelLike.TravelLike;
import com.ssafy.enjoytrip.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelLikeRepository extends JpaRepository<TravelLike, Long> {
    TravelLike findAllByAddressAndCategoryAndAddressAndImageAndLatitudeAndLongitudeAndUser(String name, String category, String address, String image, double latitude, double longitude, Users user);
    List<TravelLike> findAllByUser(Users user);
    TravelLike findAllById(Long id);
}
