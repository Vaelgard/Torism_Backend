package com.mobile.torism.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Integer id;
    private String text;
    private int upvotes;
    private int downvotes;
    private Integer userId;
    private Integer placeId;
} 