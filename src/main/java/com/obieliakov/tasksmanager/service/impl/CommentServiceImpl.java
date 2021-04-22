package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.comment.CommentDto;
import com.obieliakov.tasksmanager.dto.comment.NewCommentDto;
import com.obieliakov.tasksmanager.dto.comment.UpdateCommentDto;
import com.obieliakov.tasksmanager.mapper.CommentMapper;
import com.obieliakov.tasksmanager.model.Comment;
import com.obieliakov.tasksmanager.repository.CommentRepository;
import com.obieliakov.tasksmanager.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Validator;
import java.util.Optional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final Validator validator;

    private final CommentMapper commentMapper;

    private final CommentRepository commentRepository;

    private final IdentityService identityService;
    private final GroupMembershipService groupMembershipService;
    private final AppUserService appUserService;
    private final TaskService taskService;

    public CommentServiceImpl(Validator validator, CommentMapper commentMapper, CommentRepository commentRepository, IdentityService identityService, GroupMembershipService groupMembershipService, AppUserService appUserService, TaskService taskService) {
        this.validator = validator;
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.identityService = identityService;
        this.groupMembershipService = groupMembershipService;
        this.appUserService = appUserService;
        this.taskService = taskService;
    }

    @Override
    public Comment commentModelById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
        return comment.get();
    }

    @Override
    public CommentDto commentById(Long id) {
        Comment comment = commentModelById(id);
        groupMembershipService.verifyCurrentUserMembership(comment.getTask().getGroup().getId());
        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    public CommentDto createComment(NewCommentDto newCommentDto) {
        return null;
    }

    @Override
    public CommentDto updateComment(UpdateCommentDto updateCommentDto) {
        return null;
    }

    @Override
    public void deleteComment(Long id) {

    }
}
