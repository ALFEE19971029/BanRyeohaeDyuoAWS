package com.example.BanRyeohaedyuo.service;

import com.example.BanRyeohaedyuo.controller.dto.scrap.ScrapResponseDto;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        KakaoUser kakaoUser = kakaoUserRepository.findByUserId(userId);
        Posts posts = postsRepository.findByPostsId(postId);
        Scrap scrap = Scrap.builder()
                .kakaoUser(kakaoUser)
                .posts(posts)
                .build();
        return scrapRespository.save(scrap).getScrapId();
    }

    public Scrap findByScrapId(Long scrapId){
        return scrapRespository.findByScrapId(scrapId);
    }

    public List<ScrapResponseDto> findByUserId(Long userId){
        List<Scrap> scrapList = scrapRespository.findByUserId(userId);
        List<ScrapResponseDto> scrapResponseDtos = new ArrayList<>();
        for(Scrap s : scrapList){
            scrapResponseDtos.add(new ScrapResponseDto(s.getScrapId(),s.getPosts().getPostsId(),s.getKakaoUser().getUserId()));
        }
        return scrapResponseDtos;
    }

    public List<ScrapResponseDto> scrapForMypage(Long userIdm){
        PageRequest pageRequest = PageRequest.of(0,5, Sort.by(Sort.Direction.DESC,"createTime"));
        Page<Scrap> scrap = scrapRespository.findAll(pageRequest);
        List<Scrap> content = scrap.getContent();
        List<ScrapResponseDto> responseDtos = new ArrayList<>();
        for(Scrap s : content){
            responseDtos.add(new ScrapResponseDto(s.getScrapId(),s.getPosts().getPostsId(),s.getKakaoUser().getUserId()));
        }
        return responseDtos;
}

    public void delete(Long userId, Long scrapId){
        scrapRespository.deleteByScrapId(scrapId);
    }

}
