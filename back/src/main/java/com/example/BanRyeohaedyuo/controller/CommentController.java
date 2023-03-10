package com.example.BanRyeohaedyuo.controller;

import com.example.BanRyeohaedyuo.controller.dto.comment.CommentResponseDto;
import com.example.BanRyeohaedyuo.controller.dto.comment.CommentSaveRequestDto;
import com.example.BanRyeohaedyuo.controller.dto.comment.CommentUpdateRequestDto;
import com.example.BanRyeohaedyuo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public Long save(HttpServletRequest request, @RequestBody CommentSaveRequestDto requestDto){
        Long userId = Long.parseLong(request.getHeader("userId"));
        return commentService.save(userId, requestDto);
    }

    @GetMapping("/comment/{commentId}")
    public CommentResponseDto findById(@PathVariable Long commentId){
        return commentService.findById(commentId);
    }

    @PutMapping("/comment/{commentId}")
    public Long update(HttpServletRequest request, @PathVariable Long commentId, @RequestBody CommentUpdateRequestDto requestDto){
        Long userId = Long.parseLong(request.getHeader("userId"));
        return commentService.update(userId, commentId, requestDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public void delete(HttpServletRequest request, @PathVariable Long commentId){
        Long userId = Long.parseLong(request.getHeader("userId"));
        commentService.delete(userId, commentId);
    }
}
