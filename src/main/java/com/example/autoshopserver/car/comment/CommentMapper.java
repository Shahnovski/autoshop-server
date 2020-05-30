package com.example.autoshopserver.car.comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "car.id", target = "carId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "userUsername")
    @Mapping(source = "receiverComment.id", target = "receiverCommentId")
    @Mapping(source = "receiverComment.commentText", target = "receiverCommentText")
    CommentDTO toCommentDTO(Comment comment);

    List<CommentDTO> toCommentDTOs(List<Comment> comments);

    Comment toComment(CommentDTO commentDTO);

}
