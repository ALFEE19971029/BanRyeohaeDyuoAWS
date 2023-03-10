package com.example.BanRyeohaedyuo.controller.dto.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {
    private Long postId;
    private String description;
    private Long parentCommentId;

    @Builder
    public CommentSaveRequestDto(Long postId, String description, Long parentCommentId) {
        this.postId = postId;
        this.description = description;
        this.parentCommentId = parentCommentId;
    }
}
