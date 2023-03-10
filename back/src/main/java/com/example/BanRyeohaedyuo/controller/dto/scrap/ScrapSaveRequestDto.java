package com.example.BanRyeohaedyuo.controller.dto.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScrapSaveRequestDto {

    private Long postId;
    private String description;

    @Builder
    public ScrapSaveRequestDto(Long postId, String description) {
        this.postId = postId;
        this.description = description;
    }
}
