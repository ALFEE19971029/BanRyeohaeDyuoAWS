package com.example.BanRyeohaedyuo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private KakaoUser kakaoUser;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    private Long parentCommentId;

    @Column(length = 1500)
    private String description;

    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    @Builder
    public Comment(KakaoUser kakaoUser, Posts posts, Long parentCommentId, String description) {
        this.kakaoUser = kakaoUser;
        this.posts = posts;
        this.parentCommentId = parentCommentId;
        this.description = description;
    }

    public void update(String description){
        this.description = description;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", kakaoUser=" + kakaoUser.getKakaoId() +
                ", posts=" + posts.getPostsId() +
                ", parentCommentId=" + parentCommentId +
                ", description='" + description + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
