package com.example.BanRyeohaedyuo.controller.dto.scrap;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScrapSaveRequestDto {

    private Long postId;

    @Builder
    public ScrapSaveRequestDto(Long postId) {
        this.postId = postId;
    }
}
