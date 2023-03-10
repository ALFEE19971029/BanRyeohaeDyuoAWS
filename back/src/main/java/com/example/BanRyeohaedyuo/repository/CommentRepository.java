package com.example.BanRyeohaedyuo.repository;

import com.example.BanRyeohaedyuo.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    public Comment findByCommentId(Long commentId);
    public void deleteByCommentId(Long commentId);
}
