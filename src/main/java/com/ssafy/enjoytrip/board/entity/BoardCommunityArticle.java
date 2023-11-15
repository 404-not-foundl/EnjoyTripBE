package com.ssafy.enjoytrip.board.entity;

import com.ssafy.enjoytrip.common.entity.BaseTime;
import com.ssafy.enjoytrip.users.entity.Users;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardCommunityArticle extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    private String communityBoardTitle;

    @Lob
    private String communityBoardContext;

    private int hit;

    @OneToMany(mappedBy = "boardCommunityArticle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ParentComment> comments;
}
