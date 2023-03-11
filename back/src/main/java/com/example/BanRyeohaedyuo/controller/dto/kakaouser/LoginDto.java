package com.example.BanRyeohaedyuo.controller.dto.kakaouser;

import com.example.BanRyeohaedyuo.domain.KakaoUser;
import lombok.Getter;

@Getter
public class LoginDto {
    private String jwt;
    private KakaoUser kakaoUser;

    public LoginDto(String jwt, KakaoUser kakaoUser) {
        this.jwt = jwt;
        this.kakaoUser = kakaoUser;
    }
}
