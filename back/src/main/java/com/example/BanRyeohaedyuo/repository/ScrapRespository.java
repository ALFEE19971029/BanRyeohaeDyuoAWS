package com.example.BanRyeohaedyuo.repository;

import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScrapRespository extends JpaRepository<Scrap, Long> {
    public Scrap findByScrapId(Long scrapId);

    @Query(value = "SELECT s FROM Scrap s JOIN FETCH s.kakaoUser WHERE s.kakaoUser.userId = :userId ORDER BY s.createTime DESC")
    public List<Scrap> findByUserId(@Param("userId")Long userId);

    public void deleteByScrapId(Long scrapId);

    @Query(value = "SELECT s FROM Scrap s WHERE s.kakaoUser.userId = :userId")
    public Page<Scrap> scrapForMypage(Long userId, Pageable pageable);
}
