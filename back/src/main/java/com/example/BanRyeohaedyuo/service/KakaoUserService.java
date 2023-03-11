package com.example.BanRyeohaedyuo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.enumtype.Grade;
import com.example.BanRyeohaedyuo.domain.login.JwtProperties;
import com.example.BanRyeohaedyuo.domain.login.KakaoProfile;
import com.example.BanRyeohaedyuo.domain.login.OauthToken;
import com.example.BanRyeohaedyuo.repository.KakaoUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class KakaoUserService {

    KakaoUserRepository kakaoUserRepository;

    @Autowired
    public KakaoUserService(KakaoUserRepository kakaoUserRepository){
        this.kakaoUserRepository = kakaoUserRepository;
    }

    private String RestApiKey = "3d10b8b23a5d95b5cc73a5c8da96b089";
    private String RedirectURL = "http://localhost:3000/oauth";



    public OauthToken getAccessToken(String code){
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", RestApiKey);
        params.add("redirect_uri", RedirectURL);
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> accessTokenResponse = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        OauthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(accessTokenResponse.getBody(), OauthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return oauthToken;
    }

    public String saveUserAndGetToken(String token) {
        KakaoProfile profile = findProfile(token);

        KakaoUser kakaoUser = kakaoUserRepository.findByKakaoNickname(profile.getProperties().getNickname());

        if(kakaoUser == null) {
            kakaoUser = KakaoUser.builder()
                .kakaoId(String.valueOf(profile.getId()))
                .kakaoProfileImg(profile.getKakao_account().getProfile().getProfile_image_url())
                .kakaoNickname(profile.getKakao_account().getProfile().getNickname())
                .kakaoEmail(profile.getKakao_account().getEmail())
                .userRole("ROLE_USER")
                .grade(Grade.UNRANKED)
                .point(0)
                .build();
            kakaoUserRepository.save(kakaoUser);
        }

        return createToken(kakaoUser);
    }

    public String createToken(KakaoUser kakaoUser){
        String jwtToken = JWT.create()
                .withSubject(String.valueOf(kakaoUser.getUserId()))
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("kakaoNickname",kakaoUser.getKakaoNickname())
                .withClaim("kakaoId",kakaoUser.getKakaoId())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));
        return jwtToken;
    }

    public KakaoProfile findProfile(String token){
        RestTemplate rt = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(headers);

        ResponseEntity<String> kakaoProfileResponse = rt.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper.readValue(kakaoProfileResponse.getBody(), KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return kakaoProfile;
    }
}
