package com.example.BanRyeohaedyuo.domain;

import com.example.BanRyeohaedyuo.domain.embedded.Address;
import com.example.BanRyeohaedyuo.domain.enumtype.Category;
import com.example.BanRyeohaedyuo.domain.enumtype.Purpose;
import com.example.BanRyeohaedyuo.domain.enumtype.Status;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long postsId;//auto-increment

    @ManyToOne
    @JoinColumn(name = "user_id")
    private KakaoUser kakaoUser;

    @Column(length=500, nullable=false)
    private String title;

    @Column(length = 2000)
    @NotNull
    private String description;

    @AttributeOverride(name = "detailAddress", column = @Column(name = "detail_address"))
    @Embedded
    private Address address;

    @Column(length = 1500)
    private String image1;
    @Column(length = 1500)
    private String image2;
    @Column(length = 1500)
    private String image3;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull
    private int point;

    @Enumerated(EnumType.STRING)
    private Purpose purpose;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "update_time")
    @UpdateTimestamp
    private Timestamp updateTime;

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;

    public void setStatus(Status status) {
        this.status = status;
    }

    @Builder
    public Posts(KakaoUser kakaoUser, String title, String description, Address address, String image1, String image2, String image3, Category category, int point, Purpose purpose, Status status) {
        this.kakaoUser = kakaoUser;
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
    }


    public void update(String title, String description, Address address, String image1, String image2, String image3, Category category) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "postsId=" + postsId +
                ", kakaoUser=" + kakaoUser.getKakaoId() +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", image1='" + image1 + '\'' +
                ", image2='" + image2 + '\'' +
                ", image3='" + image3 + '\'' +
                ", category=" + category +
                ", point=" + point +
                ", purpose=" + purpose +
                ", status=" + status +
                ", createTime=" + createTime +
                '}';
    }
}
