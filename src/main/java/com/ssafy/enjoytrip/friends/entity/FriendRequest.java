package com.ssafy.enjoytrip.friends.entity;

import com.ssafy.enjoytrip.users.entity.Users;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_request_id")
    private Users requestUser;

    @ManyToOne
    @JoinColumn(name = "user_response_id")
    private Users responseUser;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime requested_date;
}
