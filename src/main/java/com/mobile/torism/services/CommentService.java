package com.mobile.torism.services;

import com.mobile.torism.dto.CommentDTO;
import com.mobile.torism.entities.Comment;
import com.mobile.torism.entities.OurUsers;
import com.mobile.torism.entities.Place;
import com.mobile.torism.mappers.CommentMapper;
import com.mobile.torism.repositories.CommentRepository;
import com.mobile.torism.repositories.PlaceRepository;
import com.mobile.torism.repositories.UsersRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final UsersRepo usersRepo;
    private final PlaceRepository placeRepository;
    private final CommentMapper commentMapper;

    public CommentDTO createComment(CommentDTO commentDTO) {
        OurUsers user = usersRepo.findById(commentDTO.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        Place place = placeRepository.findById(commentDTO.getPlaceId())
            .orElseThrow(() -> new EntityNotFoundException("Place not found"));

        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setUser(user);
        comment.setPlace(place);
        
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDTO(savedComment);
    }

    public CommentDTO upvoteComment(Integer id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        
        comment.setUpvotes(comment.getUpvotes() + 1);
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toDTO(updatedComment);
    }

    public CommentDTO downvoteComment(Integer id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        
        comment.setDownvotes(comment.getDownvotes() + 1);
        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.toDTO(updatedComment);
    }
} 