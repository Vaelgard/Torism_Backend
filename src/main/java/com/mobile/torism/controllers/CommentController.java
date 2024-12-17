package com.mobile.torism.controllers;

import com.mobile.torism.dto.CommentDTO;
import com.mobile.torism.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    @PostMapping("/upvote/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CommentDTO> upvoteComment(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.upvoteComment(id));
    }

    @PostMapping("/downvote/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CommentDTO> downvoteComment(@PathVariable Integer id) {
        return ResponseEntity.ok(commentService.downvoteComment(id));
    }
} 
