package com.example.BanRyeohaedyuo.controller.dto.posts;

import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.embedded.Address;
import com.example.BanRyeohaedyuo.domain.enumtype.Category;
import com.example.BanRyeohaedyuo.domain.enumtype.Purpose;
import com.example.BanRyeohaedyuo.domain.enumtype.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostsUpdateRequestDto {
    private String title;
    private String description;

    private Address address;

    private String image1;
    private String image2;
    private String image3;

    //받아서 enum으로 변환시켜야 함.
    private String category;

    @Builder
    public PostsUpdateRequestDto(String title, String description, Address address, String image1, String image2, String image3, String category) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.category = category;
    }
}
