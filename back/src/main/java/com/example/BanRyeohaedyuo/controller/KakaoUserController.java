package com.example.BanRyeohaedyuo.controller;

import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.login.JwtProperties;
import com.example.BanRyeohaedyuo.domain.login.OauthToken;
import com.example.BanRyeohaedyuo.repository.KakaoUserRepository;
import com.example.BanRyeohaedyuo.service.KakaoUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class KakaoUserController {

    private KakaoUserService kakaoUserService;
    private KakaoUserRepository kakaoUserRepository;

    @Autowired
    public KakaoUserController(KakaoUserService kakaoUserService, KakaoUserRepository kakaoUserRepository) {
        this.kakaoUserService = kakaoUserService;
        this.kakaoUserRepository = kakaoUserRepository;
    }

    @GetMapping("/oauth")
    public ResponseEntity getLogin(@RequestParam("code") String code){
        OauthToken oauthToken = kakaoUserService.getAccessToken(code);

        String jwtToken = kakaoUserService.saveUserAndGetToken(oauthToken.getAccess_token());

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);

        return ResponseEntity.ok().headers(headers).body("success");
    }
}
