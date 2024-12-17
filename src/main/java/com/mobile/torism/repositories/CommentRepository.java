package com.mobile.torism.repositories;

import com.mobile.torism.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByPlaceId(Integer placeId);
    List<Comment> findByUserId(Integer userId);
} 