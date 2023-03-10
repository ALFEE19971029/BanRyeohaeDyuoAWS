package com.example.BanRyeohaedyuo.service;

import com.example.BanRyeohaedyuo.controller.dto.scrap.ScrapSaveRequestDto;
import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.Scrap;
import com.example.BanRyeohaedyuo.repository.KakaoUserRepository;
import com.example.BanRyeohaedyuo.repository.PostsRepository;
import com.example.BanRyeohaedyuo.repository.ScrapRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScrapService {

    private final ScrapRespository scrapRespository;
    private final KakaoUserRepository kakaoUserRepository;
    private final PostsRepository postsRepository;
    public Long save(Long userId, ScrapSaveRequestDto requestDto){
        Long postId = requestDto.getPostId();
        String description = requestDto.getDescription();
        KakaoUser kakaoUser = kakaoUserRepository.findByUserId(userId);
        Posts posts = postsRepository.findByPostsId(postId);
        Scrap scrap = Scrap.builder()
                .kakaoUser(kakaoUser)
                .posts(posts)
                .description(description)
                .build();
        return scrapRespository.save(scrap).getScrapId();
    }

    public Scrap findByScrapId(Long scrapId){
        return scrapRespository.findByScrapId(scrapId);
    }

    public List<Scrap> findByUserId(Long userId){
        return scrapRespository.findByUserId(userId);
    }

    public List<Scrap> findByPageNum(Integer pageNum){
        pageNum -= 1;
        PageRequest pageRequest = PageRequest.of(pageNum,9);
        Page<Scrap> scrap = scrapRespository.findAll(pageRequest);
        List<Scrap> content = scrap.getContent();
        return content;
}

    public void delete(Long userId, Long scrapId){
        //해당 user가 쓴글인지 검증
        //후에 작성
        scrapRespository.deleteByScrapId(scrapId);
    }

}
