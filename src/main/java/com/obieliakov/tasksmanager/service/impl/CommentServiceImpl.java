package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.comment.CommentDto;
import com.obieliakov.tasksmanager.dto.comment.NewCommentDto;
import com.obieliakov.tasksmanager.dto.comment.UpdateCommentDto;
import com.obieliakov.tasksmanager.mapper.CommentMapper;
import com.obieliakov.tasksmanager.repository.CommentRepository;
import com.obieliakov.tasksmanager.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;

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
    public CommentDto commentById(Long id) {
        return null;
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
