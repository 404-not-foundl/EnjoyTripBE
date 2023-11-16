package com.ssafy.enjoytrip.common.address.image;

import lombok.Getter;

@Getter
public enum ProfileImageDomain {

    CACHE_DOMAIN("/static/uploadFile/userProfile/cache/"),
    PROFILE_IMAGE_DOMAIN("/static/uploadFile/userProfile/");

    private final String domain;

    ProfileImageDomain(String domain) { this.domain = domain; }
}
