package com.example.BanRyeohaedyuo.controller.dto.posts;

import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.embedded.Address;
import com.example.BanRyeohaedyuo.domain.enumtype.Category;
import com.example.BanRyeohaedyuo.domain.enumtype.Purpose;
import com.example.BanRyeohaedyuo.domain.enumtype.Status;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class PostsResponseDto {
    private Long postId;
    private String kakaoNickname;
    private String title;
    private String description;
    private Address address;
    private String image1;
    private String image2;
    private String image3;
    private Category category;
    private int point;
    private Purpose purpose;
    private Status status;
    private Timestamp updateTime;
    private Timestamp createTime;

    @Builder
    public PostsResponseDto(Long postId, String kakaoNickname, String title, String description, Address address, String image1, String image2, String image3, Category category, int point, Purpose purpose, Status status, Timestamp updateTime, Timestamp createTime) {
        this.postId = postId;
        this.kakaoNickname = kakaoNickname;
        this.title = title;
        this.description = description;
        this.address = address;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.category = category;
        this.point = point;
        this.purpose = purpose;
        this.status = status;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }
}
