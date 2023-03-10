package com.example.BanRyeohaedyuo.domain;

import com.example.BanRyeohaedyuo.domain.enumtype.Grade;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@Table(name = "kakao_user")
public class KakaoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "kakao_id")
    private String kakaoId;

    @Column(name = "kakao_profile_img", length = 1500)
    private String kakaoProfileImg;

    @NotNull
    @Column(name = "kakao_nickname")
    private String kakaoNickname;

    @Column(name = "kakao_email")
    private String kakaoEmail;

    @Column(name = "user_role")
    private String userRole;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "grade")
    private Grade grade;

    @Column(name = "point")
    private int point;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    @Builder
    public KakaoUser(String kakaoId, String kakaoProfileImg, String kakaoNickname, String kakaoEmail, String userRole, Grade grade, int point) {
        this.kakaoId = kakaoId;
        this.kakaoProfileImg = kakaoProfileImg;
        this.kakaoNickname = kakaoNickname;
        this.kakaoEmail = kakaoEmail;
        this.userRole = userRole;
        this.grade = grade;
        this.point = point;
    }
}