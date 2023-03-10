package com.example.BanRyeohaedyuo.domain;

import com.example.BanRyeohaedyuo.domain.enumtype.Grade;
import com.example.BanRyeohaedyuo.repository.KakaoUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KakaoUserTableTest {
    @Autowired
    KakaoUserRepository repository;

    @Test
    public void entityTableMappingTest(){
        KakaoUser testUser = KakaoUser.builder().kakaoId("test1").kakaoProfileImg("test2")
                .kakaoNickname("test3").kakaoEmail("test4").userRole("test5").grade(Grade.SEMIPRO).point(10).build();

        repository.save(testUser);
    }
}