package com.ssafy.enjoytrip.board.entity;

import com.ssafy.enjoytrip.common.entity.BaseTime;
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
    private Long parentCommentId;

    @ManyToOne
    @JoinColumn(name = "board_community_article_id", referencedColumnName = "boardCommunityArticleId")
    private BoardCommunityArticle boardCommunityArticle;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParentCommentLike> likes;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildComment> comments;

}
