package com.example.BanRyeohaedyuo.controller.dto.scrap;

import lombok.Getter;

@Getter
public class ScrapUpdateRequestDto {
    private String description;

    public ScrapUpdateRequestDto(String description) {
        this.description = description;
    }
}
