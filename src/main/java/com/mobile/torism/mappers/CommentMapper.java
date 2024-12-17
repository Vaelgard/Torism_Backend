package com.mobile.torism.mappers;

import com.mobile.torism.dto.CommentDTO;
import com.mobile.torism.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "place.id", target = "placeId")
    CommentDTO toDTO(Comment comment);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "placeId", target = "place.id")
    Comment toEntity(CommentDTO commentDTO);

    List<CommentDTO> toDTOList(List<Comment> comments);
} 