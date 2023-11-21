package com.ssafy.enjoytrip.board.entity.community;

import com.ssafy.enjoytrip.users.entity.Users;
import lombok.*;

import javax.persistence.*;

@Getter @Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommunityArticleLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_community_article_id")
    private BoardCommunityArticle boardCommunityArticle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
