package com.ssafy.enjoytrip.common.address;

import lombok.Getter;

@Getter
public enum DomainName {

    DOMAIN_NAME("http://localhost:8080");

    private final String domain;

    DomainName(String domain) { this.domain = domain; }

}
