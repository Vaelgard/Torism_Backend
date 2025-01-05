package com.mobile.torism.controllers;

import com.mobile.torism.dto.CommentDTO;
import com.mobile.torism.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable Integer id) {
        System.out.println("hi");
        return ResponseEntity.ok(commentService.getAllComments(id));
    }
}
