package com.ssafy.enjoytrip.users.entity;

import com.ssafy.enjoytrip.common.entity.BaseTime;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String userLoginId;

    @Column(nullable = false, length = 100)
    private String userPassword;

    @Builder.Default  // Use @Builder.Default to set a default value
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    @Column(nullable = false, unique = true, length = 100)
    private String userNickname;

    @Column(nullable = false, length = 100)
    private String userNationality;

    @Column(nullable = false, length = 100)
    private String userPhoneNumber;

    @Column(nullable = false, length = 100)
    private String userEmail;

    @Column(length = 100)
    private String userProfileImage;
}
