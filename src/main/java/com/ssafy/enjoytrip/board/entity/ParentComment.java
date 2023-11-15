package com.ssafy.enjoytrip.board.entity;

import com.ssafy.enjoytrip.common.entity.BaseTime;
import com.ssafy.enjoytrip.users.entity.Users;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParentComment extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_community_article_id", referencedColumnName = "boardCommunityArticleId")
    private BoardCommunityArticle boardCommunityArticle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String context;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParentCommentLike> likes;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildComment> comments;

}
