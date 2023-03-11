package com.example.BanRyeohaedyuo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "scrap")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scrap_id")
    private Long scrapId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private KakaoUser kakaoUser;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts posts;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    @Builder
    public Scrap(KakaoUser kakaoUser, Posts posts) {
        this.kakaoUser = kakaoUser;
        this.posts = posts;
    }
}
