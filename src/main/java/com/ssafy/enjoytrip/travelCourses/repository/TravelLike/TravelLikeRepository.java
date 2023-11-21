package com.ssafy.enjoytrip.travelCourses.repository.TravelLike;

import com.ssafy.enjoytrip.travelCourses.entity.TravelLike.TravelLike;
import com.ssafy.enjoytrip.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelLikeRepository extends JpaRepository<TravelLike, Long> {
    List<TravelLike> findAllByUser(Users user);

    TravelLike findByUserAndContentIdAndContentTypeId(Users user, String contentId, String contentTypeId);
    TravelLike findByNameAndAddressAndCategoryAndLatitudeAndLongitudeAndUser(String name, String address, String category, double lat, double lng, Users user);
}
