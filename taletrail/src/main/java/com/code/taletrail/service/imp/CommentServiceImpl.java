package com.code.taletrail.service.imp;

import com.code.taletrail.exception.ResourceNotFoundException;
import com.code.taletrail.model.Comment;
import com.code.taletrail.model.Post;
import com.code.taletrail.payload.CommentDto;
import com.code.taletrail.repository.CommentRepo;
import com.code.taletrail.repository.PostRepo;
import com.code.taletrail.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post ", "Id ",postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment ", "Id ",commentId));
        commentRepo.delete(comment);
    }
}
