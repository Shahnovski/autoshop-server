package com.example.autoshopserver.car.comment;

import com.example.autoshopserver.auth.info.AuthInfoService;
import com.example.autoshopserver.car.Car;
import com.example.autoshopserver.car.CarRepository;
import com.example.autoshopserver.exception.exceptions.CarNotFoundException;
import com.example.autoshopserver.exception.exceptions.CommentNotFoundException;
import com.example.autoshopserver.exception.exceptions.UserNotFoundException;
import com.example.autoshopserver.user.User;
import com.example.autoshopserver.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final AuthInfoService authInfoService;

    public List<CommentDTO> getCommentList(Long carId, Authentication authentication) {
        List<Comment> comments = commentRepository.findByCarId(carId, Sort.by(Sort.Direction.ASC, "id"));
        return commentMapper.toCommentDTOs(comments);
    }

    public CommentDTO getCommentById(Long id, Authentication authentication) {
        Comment comment = commentRepository.findById(id).orElseThrow(CommentNotFoundException::new);
        return commentMapper.toCommentDTO(comment);
    }

    public CommentDTO saveComment(Long id, CommentDTO commentDTO, Authentication authentication) {
        Comment comment = commentMapper.toComment(commentDTO);
        if (id != null) {
            comment.setId(id);
        }
        User user = authInfoService.getUserByAuthentication(authentication);
        Car car = carRepository.findById(commentDTO.getCarId()).orElseThrow(CarNotFoundException::new);
        comment.setCar(car);
        comment.setUser(user);
        if (commentDTO.getReceiverCommentId() != null) {
            Comment receiverComment = commentRepository.findById(commentDTO.getReceiverCommentId())
                    .orElseThrow(CommentNotFoundException::new);
            comment.setReceiverComment(receiverComment);
        }
        return commentMapper.toCommentDTO(commentRepository.save(comment));
    }

    public void deleteComment(Long id, Authentication authentication) {
        commentRepository.deleteById(id);
    }
}
