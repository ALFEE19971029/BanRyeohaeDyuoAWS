package com.example.BanRyeohaedyuo.service;

import com.example.BanRyeohaedyuo.controller.dto.posts.PostsWithScrapResponse;
import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.Scrap;
import com.example.BanRyeohaedyuo.domain.embedded.Address;
import com.example.BanRyeohaedyuo.domain.enumtype.Category;
import com.example.BanRyeohaedyuo.domain.enumtype.Purpose;
import com.example.BanRyeohaedyuo.domain.enumtype.Status;
import com.example.BanRyeohaedyuo.repository.KakaoUserRepository;
import com.example.BanRyeohaedyuo.repository.PostsRepository;
import com.example.BanRyeohaedyuo.controller.dto.posts.PostsResponseDto;
import com.example.BanRyeohaedyuo.controller.dto.posts.PostsSaveRequestDto;
import com.example.BanRyeohaedyuo.controller.dto.posts.PostsUpdateRequestDto;
import com.example.BanRyeohaedyuo.repository.ScrapRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final KakaoUserRepository kakaoUserRepository;
    private final ScrapRespository scrapRespository;

    @Autowired
    public PostsService(PostsRepository postsRepository, KakaoUserRepository kakaoUserRepository, ScrapRespository scrapRespository) {
        this.postsRepository = postsRepository;
        this.kakaoUserRepository = kakaoUserRepository;
        this.scrapRespository = scrapRespository;
    }

    @Transactional
    public Long save(Long userId, PostsSaveRequestDto requestDto) {
        KakaoUser kakaoUser = kakaoUserRepository.findByUserId(userId);
        Category category = Category.valueOf(requestDto.getCategory());
        Purpose purpose = Purpose.valueOf(requestDto.getPurpose());
        Posts posts = Posts.builder()
                .kakaoUser(kakaoUser)
                .title(requestDto.getTitle())
                .description(requestDto.getDescription())
                .address(requestDto.getAddress())
                .image1(requestDto.getImage1())
                .image2(requestDto.getImage2())
                .image3(requestDto.getImage3())
                .category(category)
                .point(0)
                .purpose(purpose)
                .status(Status.STILL)
                .build();
        return postsRepository.save(posts).getPostsId();
    }

    @Transactional
    public Long update(Long userId, Long postsId, PostsUpdateRequestDto requestDto){
        //해당 user가 쓴글인지 검증
        //후에 작성
        Posts posts = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. postsId="+postsId));

        //enum으로 변환
        Category category = Category.valueOf(requestDto.getCategory());

        posts.update(requestDto.getTitle(), requestDto.getDescription(), requestDto.getAddress(), requestDto.getImage1(), requestDto.getImage2(),
                requestDto.getImage3(), category);

        return postsId;
    }

    @Transactional
    public PostsWithScrapResponse findById(Long userId, Long postsId){
        Posts entity = postsRepository.findById(postsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ postsId));
        String kakaoNickname = entity.getKakaoUser().getKakaoNickname();
        Address address = entity.getAddress();
        Category category = entity.getCategory();
        Purpose purpose = entity.getPurpose();
        List<Scrap> scraps = scrapRespository.findByUserId(userId);
        for(Scrap s: scraps){
            if(s.getPosts().getPostsId().equals(postsId)){
                return PostsWithScrapResponse.builder()
                        .postId(postsId)
                        .kakaoNickname(kakaoNickname)
                        .title(entity.getTitle())
                        .description(entity.getDescription())
                        .address(address)
                        .image1(entity.getImage1())
                        .image2(entity.getImage2())
                        .image3(entity.getImage3())
                        .category(category)
                        .point(entity.getPoint())
                        .purpose(purpose)
                        .status(entity.getStatus())
                        .scrap(true)
                        .updateTime(entity.getUpdateTime())
                        .createTime(entity.getCreateTime())
                        .build();
            }
        }
        return PostsWithScrapResponse.builder()
                .postId(postsId)
                .kakaoNickname(kakaoNickname)
                .title(entity.getTitle())
                .description(entity.getDescription())
                .address(address)
                .image1(entity.getImage1())
                .image2(entity.getImage2())
                .image3(entity.getImage3())
                .category(category)
                .point(entity.getPoint())
                .purpose(purpose)
                .status(entity.getStatus())
                .scrap(false)
                .updateTime(entity.getUpdateTime())
                .createTime(entity.getCreateTime())
                .build();
    }

    @Transactional
    public void delete(Long userId, Long postsId){
        //해당 user가 쓴글인지 검증
        //후에 작성
        postsRepository.deleteByPostsId(postsId);
    }

    @Transactional
    public Long changeStatus(Long userId, Long postId){
        //해당 user가 쓴글인지 검증
        //후에 작성

        Posts posts = postsRepository.findByPostsId(postId);
        if(posts.getStatus() != Status.STILL){
            throw new IllegalStateException("아직 찾는 중일 때만 상태변경 가능!");
        }

        posts.setStatus(Status.DONE);
        return posts.getPostsId();
    }

    @Transactional
    public List<PostsResponseDto> findByUserId(Long userId){
        List<Posts> posts = postsRepository.findByUserId(userId);
        List<PostsResponseDto> responseDtos = new ArrayList<>();
        for(Posts p : posts){
            responseDtos.add(PostsResponseDto.builder()
                    .postId(p.getPostsId())
                    .kakaoNickname(p.getKakaoUser().getKakaoNickname())
                    .title(p.getTitle())
                    .description(p.getDescription())
                    .address(p.getAddress())
                    .image1(p.getImage1())
                    .image2(p.getImage2())
                    .image3(p.getImage3())
                    .category(p.getCategory())
                    .point(p.getPoint())
                    .purpose(p.getPurpose())
                    .status(p.getStatus())
                    .updateTime(p.getUpdateTime())
                    .createTime(p.getCreateTime())
                    .build());
        }
        return responseDtos;
    }

    @Transactional
    public List<PostsResponseDto> findByPageNum(Integer pageNum){
        pageNum -= 1;
        PageRequest pageRequest = PageRequest.of(pageNum,5, Sort.Direction.DESC, "updateTime");
        Page<Posts> posts = postsRepository.findAll(pageRequest);
        List<Posts> content = posts.getContent();
        List<PostsResponseDto> responseDtos = new ArrayList<>();
        for(Posts p : content){
            responseDtos.add(PostsResponseDto.builder()
                    .postId(p.getPostsId())
                    .kakaoNickname(p.getKakaoUser().getKakaoNickname())
                    .title(p.getTitle())
                    .description(p.getDescription())
                    .address(p.getAddress())
                    .image1(p.getImage1())
                    .image2(p.getImage2())
                    .image3(p.getImage3())
                    .category(p.getCategory())
                    .point(p.getPoint())
                    .purpose(p.getPurpose())
                    .status(p.getStatus())
                    .updateTime(p.getUpdateTime())
                    .createTime(p.getCreateTime())
                    .build());
        }
        return responseDtos;
    }

    @Transactional
    public List<PostsResponseDto> postsForMypage(Long userId){
        PageRequest pageRequest = PageRequest.of(0,5, Sort.Direction.DESC, "updateTime");
        KakaoUser kakaoUser = kakaoUserRepository.findByUserId(userId);
        List<Posts> posts = postsRepository.findByKakaoUser(kakaoUser,pageRequest);
        List<PostsResponseDto> responseDtos = new ArrayList<>();
        for(Posts p : posts){
            responseDtos.add(PostsResponseDto.builder()
                    .postId(p.getPostsId())
                    .kakaoNickname(p.getKakaoUser().getKakaoNickname())
                    .title(p.getTitle())
                    .description(p.getDescription())
                    .address(p.getAddress())
                    .image1(p.getImage1())
                    .image2(p.getImage2())
                    .image3(p.getImage3())
                    .category(p.getCategory())
                    .point(p.getPoint())
                    .purpose(p.getPurpose())
                    .status(p.getStatus())
                    .updateTime(p.getUpdateTime())
                    .createTime(p.getCreateTime())
                    .build());
        }
        return responseDtos;
    }
}
