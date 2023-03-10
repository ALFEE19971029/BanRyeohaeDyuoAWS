package com.example.BanRyeohaedyuo.controller;

import com.example.BanRyeohaedyuo.controller.dto.posts.PostsSaveRequestDto;
import com.example.BanRyeohaedyuo.controller.dto.scrap.ScrapSaveRequestDto;
import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.Scrap;
import com.example.BanRyeohaedyuo.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScrapController {
    private final ScrapService scrapService;

    @PostMapping("/scrap")
    public Long save(HttpServletRequest request, @RequestBody ScrapSaveRequestDto requestDto){
        //id필드 리퀘스트에서 가져오기 -> 넘기기
        Long userId = Long.parseLong(request.getHeader("userId"));
        return scrapService.save(userId,requestDto);
    }

    @GetMapping("/scrap")
    public List<Scrap> findByUserId(HttpServletRequest request){
        Long userId = Long.parseLong(request.getHeader("userId"));
        return scrapService.findByUserId(userId);
    }

    @GetMapping("/scrap/page/{pageNum}")
    public List<Scrap> findByPageNum(@PathVariable Integer pageNum){
        return scrapService.findByPageNum(pageNum);
    }

    @DeleteMapping("/scrap/{scrapId}")
    public void delete(HttpServletRequest request, @PathVariable Long scrapId){
        Long userId = Long.parseLong(request.getHeader("userId"));
        scrapService.delete(userId, scrapId);
    }
}
