package com.example.BanRyeohaedyuo.controller;

import com.example.BanRyeohaedyuo.controller.dto.posts.PostsSaveRequestDto;
import com.example.BanRyeohaedyuo.controller.dto.scrap.ScrapResponseDto;
import com.example.BanRyeohaedyuo.controller.dto.scrap.ScrapSaveRequestDto;
import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.domain.Scrap;
import com.example.BanRyeohaedyuo.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
        Long userId = Long.parseLong(request.getHeader("userId"));
        //Long userId = (Long) request.getAttribute("userId");
        return scrapService.save(userId,requestDto);
    }

    @GetMapping("/scrap")
    public List<ScrapResponseDto> findByUserId(HttpServletRequest request){
        Long userId = Long.parseLong(request.getHeader("userId"));
        //Long userId = (Long) request.getAttribute("userId");
        return scrapService.findByUserId(userId);
    }

    @GetMapping("/scrap/mypage")
    public List<ScrapResponseDto> scrapForMypage(HttpServletRequest request){
        Long userId = Long.parseLong(request.getHeader("userId"));
        //Long userId = (Long) request.getAttribute("userId");
        return scrapService.scrapForMypage(userId);
    }

    @DeleteMapping("/scrap/{scrapId}")
    public ResponseEntity delete(HttpServletRequest request, @PathVariable Long scrapId){
        Long userId = Long.parseLong(request.getHeader("userId"));
        //Long userId = (Long) request.getAttribute("userId");
        scrapService.delete(userId, scrapId);
        return ResponseEntity.ok().body("success");
    }
}
