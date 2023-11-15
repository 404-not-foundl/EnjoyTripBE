package com.ssafy.enjoytrip.board.entity;

import com.ssafy.enjoytrip.users.entity.Users;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChildCommentLike {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "child_comment_id")
    private ChildComment childComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;
}
