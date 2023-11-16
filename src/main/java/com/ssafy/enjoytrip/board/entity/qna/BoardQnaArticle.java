package com.ssafy.enjoytrip.board.entity.qna;

import com.ssafy.enjoytrip.common.entity.BaseTime;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardQnaArticle extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    private String userNick;
    private String title;
    @Lob
    private String content;
    private int hit;
}
