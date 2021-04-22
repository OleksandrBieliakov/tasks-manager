package com.obieliakov.tasksmanager.service.impl;

import com.obieliakov.tasksmanager.dto.comment.CommentDto;
import com.obieliakov.tasksmanager.dto.comment.NewCommentDto;
import com.obieliakov.tasksmanager.dto.comment.UpdateCommentDto;
import com.obieliakov.tasksmanager.mapper.CommentMapper;
import com.obieliakov.tasksmanager.model.AppUser;
import com.obieliakov.tasksmanager.model.Comment;
import com.obieliakov.tasksmanager.model.Task;
import com.obieliakov.tasksmanager.repository.CommentRepository;
import com.obieliakov.tasksmanager.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
    public boolean isCommentAddedByAppUser(Comment comment, AppUser appUser) {
        return comment.getAddedBy().getId().equals(appUser.getId());
    }

    @Override
    public void verifyCommentAddedByAppUser(Comment comment, UUID appUserId) {
        AppUser appUser = appUserService.appUserModelById(appUserId);
        if(!isCommentAddedByAppUser(comment, appUser)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Comment added by another user");
        }
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
        newCommentDto.trim();

        Set<ConstraintViolation<NewCommentDto>> violations = validator.validate(newCommentDto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        UUID currentAppUserID = identityService.currentUserID();
        Task task = taskService.taskModelById(newCommentDto.getTaskId());

        groupMembershipService.verifyMembership(currentAppUserID, task.getGroup().getId());

        Comment newComment = new Comment();
        newComment.setMessage(newCommentDto.getMessage());
        newComment.setTask(task);
        newComment.setAddedBy(appUserService.appUserModelById(currentAppUserID));

        Comment createdComment = commentRepository.save(newComment);
        return commentMapper.commentToCommentDto(createdComment);
    }

    @Override
    public CommentDto updateComment(Long id, UpdateCommentDto updateCommentDto) {
        Comment comment = commentModelById(id);

        UUID currentAppUserID = identityService.currentUserID();

        groupMembershipService.verifyMembership(currentAppUserID, comment.getTask().getGroup().getId());

        verifyCommentAddedByAppUser(comment, currentAppUserID);

        comment.setMessage(updateCommentDto.getMessage());

        Comment updatedComment = commentRepository.save(comment);
        return commentMapper.commentToCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentModelById(id);

        UUID currentAppUserID = identityService.currentUserID();

        groupMembershipService.verifyMembership(currentAppUserID, comment.getTask().getGroup().getId());

        verifyCommentAddedByAppUser(comment, currentAppUserID);

        commentRepository.delete(comment);
    }
}
