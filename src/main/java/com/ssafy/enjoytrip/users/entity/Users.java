package com.ssafy.enjoytrip.users.entity;

import com.ssafy.enjoytrip.common.entity.BaseTime;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Builder
@Entity
@RequiredArgsConstructor
public class Users extends BaseTime {

    @Id @GeneratedValue
    private Long id;

    private String userLoginId;
    private String userPassword;
}
