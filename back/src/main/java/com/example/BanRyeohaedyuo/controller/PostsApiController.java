package com.example.BanRyeohaedyuo.controller;

import com.example.BanRyeohaedyuo.service.PostsService;
import com.example.BanRyeohaedyuo.controller.dto.posts.PostsResponseDto;
import com.example.BanRyeohaedyuo.controller.dto.posts.PostsSaveRequestDto;
import com.example.BanRyeohaedyuo.controller.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/posts")
    public Long save(HttpServletRequest request,@RequestBody PostsSaveRequestDto requestDto){
        //id필드 리퀘스트에서 가져오기 -> 넘기기
        Long userId = Long.parseLong(request.getHeader("userId"));
        return postsService.save(userId, requestDto);
    }

    @PutMapping("/posts/{postsId}")
    public Long update(HttpServletRequest request,@PathVariable Long postsId, @RequestBody PostsUpdateRequestDto requestDto){
        Long userId = Long.parseLong(request.getHeader("userId"));
        return postsService.update(userId, postsId, requestDto);
    }

    @GetMapping("/posts/{postsId}")
    public PostsResponseDto findById(@PathVariable Long postsId){
        return postsService.findById(postsId);
    }

    @DeleteMapping("/posts/{postsId}")
    public void delete(HttpServletRequest request, @PathVariable Long postsId){
        Long userId = (Long) request.getAttribute("userId");
        postsService.delete(userId, postsId);
    }

    @PostMapping("/posts/{postsId}")
    public void changeStatus(HttpServletRequest request, @PathVariable Long postsId){
        Long userId = (Long) request.getAttribute("userId");
        postsService.changeStatus(userId, postsId);
    }

    @GetMapping("/posts")
    public List<PostsResponseDto> findByUserId(HttpServletRequest request){
        Long userId = Long.parseLong(request.getHeader("userId"));
        return postsService.findByUserId(userId);
    }

    @GetMapping("/posts/page/{pageNum}")
    public List<PostsResponseDto> findByPageNum(@PathVariable Integer pageNum){
        return postsService.findByPageNum(pageNum);
    }

    @GetMapping("/posts/mypage")
    public List<PostsResponseDto> postsForMypage(HttpServletRequest request){
        Long userId = Long.parseLong(request.getHeader("userId"));
        return postsService.postsForMypage(userId);
    }
}
