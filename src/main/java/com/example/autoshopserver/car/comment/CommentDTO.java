package com.example.autoshopserver.car.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private String commentText;
    private Long carId;
    private Long userId;
    private String userUsername;
    private Long receiverCommentId;
    private String receiverCommentText;

}
