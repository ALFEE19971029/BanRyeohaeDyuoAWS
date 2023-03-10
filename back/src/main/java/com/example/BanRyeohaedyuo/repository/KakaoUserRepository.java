package com.example.BanRyeohaedyuo.repository;

import com.example.BanRyeohaedyuo.domain.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {
    public KakaoUser findByKakaoNickname(String kakaoNickname);

    public KakaoUser findByUserId(Long userId);
}
