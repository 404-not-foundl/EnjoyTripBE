package com.ssafy.enjoytrip.map.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class AttractionInfo {

    @Id
    private int contentId;

    private int contentTypeId;
    private String title;
    private String addr1;
    private String addr2;
    private String tel;
    private String firstImage;
    private String firstImage2;
    private int readcount;
    private int sidoCode;
    private int gugunCode;
    private double latitude;
    private double longitude;
    private String mlevel;
}
