package com.example.BanRyeohaedyuo.repository;

import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.Posts;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    public Posts findByPostsId(Long postsId);

    public void deleteByPostsId(Long postsId);

    @Query(value = "SELECT p FROM Posts p JOIN FETCH p.kakaoUser WHERE p.kakaoUser.userId = :userId")
    public List<Posts> findByUserId(@Param("userId")Long userId);

    public List<Posts> findByKakaoUser(KakaoUser kakaoUser, Pageable pageable);
}