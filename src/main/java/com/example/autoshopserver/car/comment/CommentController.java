package com.example.autoshopserver.car.comment;

import com.example.autoshopserver.common.ApplicationProperties;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(ApplicationProperties.COMMENT_API_URL)
public class CommentController {

    private final CommentService commentService;

    @RolesAllowed({"ADMIN", "USER"})
    @GetMapping("/{carId}")
    List<CommentDTO> getCommentList(@PathVariable(value = "carId") Long carId, Authentication authentication) {
        return commentService.getCommentList(carId, authentication);
    }

    @RolesAllowed({"ADMIN", "USER"})
    @PostMapping("")
    CommentDTO createComment(@Valid @RequestBody CommentDTO commentDTO, Authentication authentication) {
        return commentService.saveComment(null, commentDTO, authentication);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/{id}")
    public CommentDTO updateComment(@PathVariable(value = "id") Long commentId, @Valid @RequestBody CommentDTO commentDTO,
                                Authentication authentication) {
        return commentService.saveComment(commentId, commentDTO, authentication);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable(value = "id") Long commentId, Authentication authentication) {
        commentService.deleteComment(commentId, authentication);
    }
}
