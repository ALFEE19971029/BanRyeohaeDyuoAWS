package com.example.BanRyeohaedyuo.controller.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String kakaoNickname;
    private Long postsId;
    private Long parentCommentId;
    private String description;
    private Timestamp updateTime;
    private Timestamp createTime;

    @Builder
    public CommentResponseDto(Long commentId, String kakaoNickname, Long postsId, Long parentCommentId, String description, Timestamp updateTime, Timestamp createTime) {
        this.commentId = commentId;
        this.kakaoNickname = kakaoNickname;
        this.postsId = postsId;
        this.parentCommentId = parentCommentId;
        this.description = description;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "CommentResponseDto{" +
                "commentId=" + commentId +
                ", kakaoNickname='" + kakaoNickname + '\'' +
                ", postsId=" + postsId +
                ", parentCommentId=" + parentCommentId +
                ", description='" + description + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
