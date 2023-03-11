package com.example.BanRyeohaedyuo.controller.dto.scrap;

import lombok.Getter;

@Getter
public class ScrapResponseDto {
    private Long scrapId;
    private Long postId;
    private Long writerId;

    public ScrapResponseDto(Long scrapId, Long postId, Long writerId) {
        this.scrapId = scrapId;
        this.postId = postId;
        this.writerId = writerId;
    }
}
