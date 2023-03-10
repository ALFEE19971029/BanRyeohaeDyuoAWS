package com.example.BanRyeohaedyuo.service;

import com.example.BanRyeohaedyuo.controller.dto.comment.CommentResponseDto;
import com.example.BanRyeohaedyuo.controller.dto.comment.CommentSaveRequestDto;
import com.example.BanRyeohaedyuo.controller.dto.comment.CommentUpdateRequestDto;
import com.example.BanRyeohaedyuo.domain.Comment;
import com.example.BanRyeohaedyuo.domain.KakaoUser;
import com.example.BanRyeohaedyuo.domain.Posts;
import com.example.BanRyeohaedyuo.repository.CommentRepository;
import com.example.BanRyeohaedyuo.repository.KakaoUserRepository;
import com.example.BanRyeohaedyuo.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {

    private final KakaoUserRepository kakaoUserRepository;
    private final PostsRepository postsRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(KakaoUserRepository kakaoUserRepository, PostsRepository postsRepository, CommentRepository commentRepository) {
        this.kakaoUserRepository = kakaoUserRepository;
        this.postsRepository = postsRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Long save(Long userId, CommentSaveRequestDto requestDto){
        KakaoUser kakaoUser = kakaoUserRepository.findByUserId(userId);
        Posts posts = postsRepository.findByPostsId(requestDto.getPostId());
        Long parentCommentId = requestDto.getParentCommentId();
        if(parentCommentId == null){
            parentCommentId = requestDto.getPostId();
        }
        Comment comment = Comment.builder()
                .kakaoUser(kakaoUser)
                .posts(posts)
                .description(requestDto.getDescription())
                .parentCommentId(parentCommentId)
                .build();
        return commentRepository.save(comment).getCommentId();
    }

    @Transactional
    public CommentResponseDto findById(Long commentId){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new IllegalArgumentException("해당 댓글이 없습니다. commentId=" + commentId));

        return CommentResponseDto.builder()
                .commentId(commentId)
                .kakaoNickname(comment.getKakaoUser().getKakaoNickname())
                .postsId(comment.getPosts().getPostsId())
                .parentCommentId(comment.getParentCommentId())
                .description(comment.getDescription())
                .createTime(comment.getCreateTime())
                .build();
    }

    @Transactional
    public Long update(Long userId, Long commentId, CommentUpdateRequestDto requestDto){
        //사용자 검증
        Comment comment = commentRepository.findByCommentId(commentId);
        comment.update(requestDto.getDescription());
        return comment.getCommentId();
    }

    @Transactional
    public void delete(Long userId, Long commentId){
        //사용자 검증
        commentRepository.deleteByCommentId(commentId);
    }
}