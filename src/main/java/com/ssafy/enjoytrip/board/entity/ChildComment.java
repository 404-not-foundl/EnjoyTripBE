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
public class ChildComment extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childCommentId;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private ParentComment parentComment;

    @OneToMany(mappedBy = "childComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChildCommentLike> likes;


}
